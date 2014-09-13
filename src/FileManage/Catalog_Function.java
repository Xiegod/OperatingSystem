package FileManage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Catalog_Function {
	
	//----���ݸ������̿�ֱ�ȡ��Ŀ¼���ı����ܺ�------
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

	//----Ѱ�ҵ�ǰ���̿���δʹ�õģ�8�ֽڣ�-------
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
	
	public static void delCatalog(String name) throws IOException {
		Catalog catalog;
		byte[] temp =( name+"      ").getBytes();
		byte[] temp1 = new byte[3];
		temp1[0] = temp[0];
		temp1[1] = temp[1];
		temp1[2] = temp[2];
		String tempString = new String(temp1);
		
		for (int i = 0; i < 8; i++) {
			int property = Finder.disk.block[Explorer.getCDB()][i * 8 +5];
			if (property == 0 || property == 1) {
				catalog = new Catalog(Finder.disk.block[Explorer.getCDB()], i * 8);
				if (catalog.getName().equals(tempString)) {
					System.out.println("Find");
					Finder.disk.block[Explorer.getCDB()][i * 8 +5] = -1;
					if (catalog.getLength() == 0) {
						int num = catalog.getStartBlock();
						
						if(num > 63){
							Finder.disk.block[1][num % 64] = 0;
						}else {
							Finder.disk.block[0][num] = 0;
						}
					}else {
						//---ɾ���ļ�----
					}
					break;
				}
			}
		}		
		writeInDisk();
	}
	
	public static int createCatalog(String name,int type,int CDB) throws IOException{
		Catalog newCatalog;
		List<Catalog> dirCatalogs = getDirCatalogs(CDB);
		List<Catalog> fileCatalogs = getFileCatalogs(CDB);
		int num = dirCatalogs.size() + fileCatalogs.size();
		
		//----�������ƴ���--------
		if (num >= 8) {
			JOptionPane.showMessageDialog(null, "�ļ���Ŀ�������ƣ�","����",JOptionPane.CLOSED_OPTION);
			return -1;
		}
		//----0 Ϊ Ŀ¼��1 Ϊ �ļ�----
		byte[] temp =( name+"      ").getBytes();
		byte[] temp1 = new byte[3];
		temp1[0] = temp[0];
		temp1[1] = temp[1];
		temp1[2] = temp[2];
		String tempString = new String(temp1);
		
		if (type == 0) {		
			for (int i = 0; i < dirCatalogs.size(); i++) {
				if(dirCatalogs.get(i).getName().equals(tempString)){
					JOptionPane.showMessageDialog(null, "�������ѱ�ʹ��");
					return -1;
				}
				System.out.println(dirCatalogs.get(i).getName());
			}
			newCatalog = new Catalog(name+"      ", "    ", (byte)0, (byte)freeBlock(), (byte)0);
		}else {
			for (int i = 0; i < fileCatalogs.size(); i++) {
				if(fileCatalogs.get(i).getName().equals(tempString)){
					JOptionPane.showMessageDialog(null, "�������ѱ�ʹ��");
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
		System.out.println("д��ɹ�");
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
}