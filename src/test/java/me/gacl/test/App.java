package me.gacl.test;

import java.util.UUID;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;


public class App {
    
    private ZkClient zkClient;
    
    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }


    /**
     * ????
     * @param args
     */
    public static void main( String[] args ) {
       
        App bootStrap=new App();
        bootStrap.initialize();
        
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            
    }
    
  
    /**
     * ???zookeeper
     */
    public void initialize() {
        
        String connectionString="127.0.0.1:2181";
        int connectionTimeout=50000;
        
        zkClient=new ZkClient(connectionString, connectionTimeout);
        
        if(!zkClient.exists("/root1")) {
            zkClient.create("/root1", new Long(System.currentTimeMillis()), CreateMode.EPHEMERAL);
        }
            
        new Thread(new RootNodeChangeThread()).start();
    }
    
    /**
     *
     */
    private class RootNodeChangeThread implements Runnable{

        public void run() {
            
            while(true) {
            
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    //ignore
                }
                
                String uuidStr=UUID.randomUUID().toString();    
                
                System.out.println(">>>>>>>>>> ????? uuid string,'uuidStr'===>"+uuidStr);
                
                zkClient.writeData("/root1", uuidStr);
                
            }
            
        }
        
    }
}