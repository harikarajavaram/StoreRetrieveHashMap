package com.hm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Entity(name = "StoreBlob")
@Table(name="storeblob")
@Proxy(lazy=false) 
class StoreBlob{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long blobId;
	@Lob
	@Column(length=100000, columnDefinition="bytea")
	private byte[] data;
}

@Component
public class HelperClass{
    @Autowired
    StoreBlobRepository blobrep;	

	@PostConstruct
	public void helperMethod() {
		System.out.println("inside helper class");
		HashMap<Long,Integer> longintmap = new HashMap<Long,Integer>();
		longintmap.put(107352L, 1);
		longintmap.put(106877L, 2);
		longintmap.put(221789L, 3);
		storeHashMap(longintmap);
		HashMap<Long,Integer> hm = readHashMap();
		System.out.println(hm);
	}
	public void storeHashMap(HashMap<Long,Integer> longintmap) {		
        ByteArrayOutputStream bObj = new ByteArrayOutputStream();
            ObjectOutputStream out;
                try {
                out = new ObjectOutputStream(bObj);
                out.writeObject(longintmap);
                //System.out.println("harika");
               // StoreBlob b = new StoreBlob();
                //b.obj = out;
                out.close();
                bObj.close();
                byte[] byteOut = bObj.toByteArray();
            	blobrep.insertBlob(byteOut);
                
              } catch (IOException e) {
                e.printStackTrace();
            }	
      }	
	
	public HashMap<Long,Integer> readHashMap() {	
		byte[] byteIn = blobrep.readBlob();
        ByteArrayInputStream bObj = new ByteArrayInputStream(byteIn);
            ObjectInputStream out;
            HashMap longintmap1 = null;
                try {
                out = new ObjectInputStream(bObj);
                //out.writeObject(longintmap);
                System.out.println("harika");
               // StoreBlob b = new StoreBlob();
                //b.obj = out;
                try {
					longintmap1 = (HashMap) out.readObject();
					System.out.println(longintmap1);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                out.close();
                bObj.close();
                //byte[] byteOut = bObj.toByteArray();
            	//blobrep.insertBlob(byteOut);
                
              } catch (IOException e) {
                e.printStackTrace();
            }
            return longintmap1;
      }	
}
