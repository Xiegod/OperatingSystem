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

	//----寻找当前磁盘块中未使用的（8字节）-------
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
					Finder.disk.block[i][j] = (byte)255;
					return (i * 64 + j);
				}
			}
		}
		return result;
	}

	public static boolean rename(String name,String name2){
		boolean flag = true;
		byte[] temp =( name+"      ").getBytes();
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
	
	public static void delCatalog(String name,int type) throws IOException {
			
		Catalog catalog;
		
		if (type == 0) {
			for (int i = 0; i < 8; i++) {
				int property = Finder.disk.block[Explorer.getCDB()][i * 8 +5];
				if (property == 0) {
					catalog = new Catalog(Finder.disk.block[Explorer.getCDB()], i * 8);
					if (rename(name, catalog.getName())) {
						int CDB = catalog.getStartBlock();
						if (getAllCatalogs(CDB).size() > 0) {
							JOptionPane.showMessageDialog(null, "文件目录不为空。不能删除");
							return ;
						}else {
							Finder.disk.block[Explorer.getCDB()][i * 8 + 5] = -1;
							if(CDB > 63){
								Finder.disk.block[1][CDB % 64] = 0;
							}else {
								Finder.disk.block[0][CDB] = 0;
							}
						}				
					}
				}
			}
		}else {
			//----删除文件------
		}
		writeInDisk();
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
		//----0 为 目录，1 为 文件----		
		if (type == 0) {		
			for (int i = 0; i < dirCatalogs.size(); i++) {
				if(rename(name, dirCatalogs.get(i).getName())){
					JOptionPane.showMessageDialog(null, "该名字已被使用");
					return -1;
				}
				System.out.println(dirCatalogs.get(i).getName());
			}
			newCatalog = new Catalog(name+"      ", "    ", (byte)0, (byte)freeBlock(), (byte)0);
		}else {
			for (int i = 0; i < fileCatalogs.size(); i++) {
				if(rename(name, dirCatalogs.get(i).getName())){
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
		System.out.println("写入成功");
		Finder.disk.load();
		return 1;
	}
	

	public static int writeInDisk() throws IOException{
		FileOutputStream outputStream = new FileOutputStream(Finder.disk.dataFile);
		for (int i = 0; i < 128; i++) {
			outputStream.write(Finder.disk.block[i],0,64);
		}	
		outputStream.close();
		return 1;
	}

	public static void toDistinationPath(String string){
		
	}
}
