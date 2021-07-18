package com.rmtypepic.model;

public class update_DB_picture {
	
public static void main(String[] args) {

		
		UploadPicture up =new UploadPicture();
		String path="WebContent/front-end/images/";

		for(int i=1;i<=9;i++){
			up.updatePicture( i , path+i+".jpg");
		}
		


		up.updatePicture(1 , path+"1.jpg");

	}

}
