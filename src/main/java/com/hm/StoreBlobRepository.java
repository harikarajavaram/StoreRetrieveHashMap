package com.hm;

import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.HashMap;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface StoreBlobRepository extends CrudRepository<StoreBlob, Long>{

	void save(ObjectOutputStream out);

//	void save(ObjectOutputStream out);

	//void save(ObjectOutputStream out);
	public static final String storeindb = "insert into storeblob(data) values(?)";
	//public static final String createtable = "create ";

	@Transactional	
	@Modifying
	@Query(value = storeindb, nativeQuery = true)
	void insertBlob(byte[] byteOut);
	
	public static final String readfromdb = "SELECT data FROM storeblob ORDER BY blob_id DESC limit 1";
	@Transactional	
	@Query(value = readfromdb, nativeQuery = true)
	byte[] readBlob();


}
