package FileManage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Catalog_Function {
	
	//----根据给定磁盘块分别取出目录，文本及总和------
	public static List<Catalog> getDirCatalogs(int CDB) {
		List<Catalog> dirCatalogs = new ArrayList<Catalog>();
		for (int i = 0; i < 8; i++) {
			if (Finder.disk.block[CDB][i * 8 + 5] == 0 ) {
				Catalog catalog = new Catalog(Finder.disk.block[CDB], i * 8);
				dirCatalogs.add(catalog);
			}
		}
		return dirCatalogs;
	}
	
	public static List<Catalog> getFileCatalogs(int CDB) {
		List<Catalog> fileCatalogs = new ArrayList<Catalog>();
		for (int i = 0; i < 8; i++) {
			if (Finder.disk.block[CDB][i * 8 + 5] == 1 ) {
				Catalog catalog = new Catalog(Finder.disk.block[CDB], i * 8);
				fileCatalogs.add(catalog);
			}
		}
		return fileCatalogs;
	}
	
	public static List<Catalog> getAllCatalogs(int CDB) {
		List<Catalog> catalogList1,catalogList2;
		catalogList1 = getDirCatalogs(CDB);
		catalogList2 = getFileCatalogs(CDB);
		
		for (int i = 0; i < catalogList2.size(); i++) {
			catalogList1.add(catalogList2.get(i));
		}
		return catalogList1;
	}

	//----寻找空闲磁盘块和当前磁盘块中未使用的目录（8字节为单位）-------
	public static int freeEntry() {
		int result = 0;
		for (int i = 0; i <8; i++) {
			result = Finder.disk.block[Explorer.getCDB()][i * 8 + 5];
			if (result == -1) {
				result = i;
				break;
			}
		}
		return result;
	}
	
	public static int freeBlock(){
		int result = 128;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 64; j++) {
				if (Finder.disk.block[i][j] == 0) {
					Finder.disk.block[i][j] = (byte)-1;
					return (i * 64 + j);
				}
			}
		}
		return result;
	}

	//----判断同名-------
	public static boolean rename(String name,String name2){
		boolean flag = true;
		byte[] temp =( name+"      ").getBytes(); //---加空格串是避免name值不够3位时达不到匹配功能。
		byte[] temp1 = new byte[3];
		temp1[0] = temp[0];
		temp1[1] = temp[1];
		temp1[2] = temp[2];
		String tempString = new String(temp1);
		
		if (!tempString.equals(name2)) {
			flag = false;
		}
		
		return flag;
	}

	
	//----删除目录，创建目录，写入目录----
	public static int delCatalog(String name,int type) throws IOException {
			
		Catalog catalog;
		int next;
			for (int i = 0; i < 8; i++) {
				int property = Finder.disk.block[Explorer.getCDB()][i * 8 +5];
				//-----删除目录---
				if (property == type && type == 0) {
					catalog = new Catalog(Finder.disk.block[Explorer.getCDB()], i * 8);
					if (rename(name, catalog.getName())) {
						int CDB = catalog.getStartBlock();
						if (getAllCatalogs(CDB).size() > 0) {
							JOptionPane.showMessageDialog(null, "文件目录不为空。不能删除");
							return -1;
						}else {  //----清除工作，FAT表该块设置回未使用。
							Finder.disk.block[Explorer.getCDB()][i * 8 + 5] = -1;
							Finder.disk.block[CDB / 64][CDB % 64] = 0;							
							break;
						}				
					}
				}else if(property == type && property == 1){
					//----删除文件------
					catalog = new Catalog(Finder.disk.block[Explorer.getCDB()], i * 8);
					if (rename(name, catalog.getName())) {
						int CDB = catalog.getStartBlock();
						//----先清除第一块-----
						Finder.disk.block[Explorer.getCDB()][i * 8 + 5] = -1;
						next = Finder.disk.block[CDB / 64][CDB % 64];
						Finder.disk.block[CDB / 64][CDB % 64] = 0;						
//						System.out.print("下一连接块 为  "+ next);
						for (int j = 0; j < 64; j++) {
							Finder.disk.block[CDB][j] = (byte)-1;
						}
						
						//-----若文件占用不只一块，则根据数据链将所有占用的块删除----
						while (next != -1) {
							CDB = Finder.disk.block[next / 64][next % 64];
							Finder.disk.block[next / 64][next % 64] = 0;						
							for (int j = 0; j < 64; j++) {
								Finder.disk.block[next][j] = (byte)-1;
							}
							next = CDB;
						}
						break;
					}
				}
				
			}
			
		writeInDisk();
		return 1;
	}
	

	public static int createCatalog(String name,int type,int CDB) throws IOException{
		Catalog newCatalog;
		List<Catalog> dirCatalogs = getDirCatalogs(CDB);
		List<Catalog> fileCatalogs = getFileCatalogs(CDB);
		int num = dirCatalogs.size() + fileCatalogs.size();
		
		//----条件限制处理--------
		if (num >= 8) {
			JOptionPane.showMessageDialog(null, "文件数目超过限制！","警告",JOptionPane.CLOSED_OPTION);
			return -1;
		}
		
		if ( name.length() == 0) {
			JOptionPane.showMessageDialog(null, "名字不能为空!","警告",JOptionPane.CLOSED_OPTION);
			return -1;
		}
		
		
		
		//----0 为 目录，1 为 文件----		
		if (type == 0) {		
			for (int i = 0; i < dirCatalogs.size(); i++) {
				if(rename(name, dirCatalogs.get(i).getName())){
					JOptionPane.showMessageDialog(null, "该名字已被使用");
					return -1;
				}
			}
			newCatalog = new Catalog(name+"      ", "    ", (byte)0, (byte)freeBlock(), (byte)0);
		}else {
			for (int i = 0; i < fileCatalogs.size(); i++) {
				if(rename(name, fileCatalogs.get(i).getName())){
					JOptionPane.showMessageDialog(null, "该名字已被使用");
					return -1;
				}
			}
			newCatalog = new Catalog(name+"      ", "txt", (byte)1, (byte)freeBlock(), (byte)0);
		}
	
		writeCatalog(newCatalog,CDB,freeEntry());	
		return 1;
	}
		

	public static int writeCatalog(Catalog catalog,int CDB,int point) throws IOException{
		byte[] data = catalog.catalogToByte();
		for (int i = 0; i < 8; i++) {
			Finder.disk.block[CDB][point * 8 + i] = data[i];
		} 
		writeInDisk();
		Finder.disk.load();
		return 1;
	}
	
	
	//----根据目录路径到达目录---返回目录所在块-------
	public static int toDistinationPath(String string){
		List<Integer> tempPDB = Explorer.getPDB();
		int tempCDB = Explorer.getCDB();
		int CDB = 2;
		String[] names = new String[8];
		names = string.split("/");
		
		Explorer.setToTop();
		
		if (names[0].equals("C:")) {
			for (int i = 1; i < names.length; i++) {
				if((CDB = whereIscatalog(names[i], Explorer.getCDB())) != -1){
					Explorer.getPDB().add(Explorer.getCDB());
					Explorer.setCDB(CDB);
				}else{
					Explorer.setPDB(tempPDB);
					Explorer.setCDB(tempCDB);
					return 2;
				}
			}
			
		}else{
			return 3;
		}
		return 1;
	}
	
	public static int whereIscatalog(String name,int CDB){
		int cdb = -1;
		List<Catalog> catalogs = getAllCatalogs(CDB);
		for (int i = 0; i < catalogs.size(); i++) {
			if (rename(name, catalogs.get(i).getName())) {
				return catalogs.get(i).getStartBlock();
			}
		}		
		return cdb;
	}
	
	//----将所有磁盘块写入磁盘文件------
	public static int writeInDisk() throws IOException{
		FileOutputStream outputStream = new FileOutputStream(Finder.disk.dataFile);
		for (int i = 0; i < 128; i++) {
			outputStream.write(Finder.disk.block[i],0,64);
		}	
		outputStream.close();
		return 1;
	}


}
