package FileManage;

import java.util.ArrayList;
import java.util.List;

public class Catalog_Function {
	//----根据给定磁盘块分别取出目录，文本及总和------
	public static List<Catalog> getDirCatalogs(int CDB) {
		List<Catalog> dirCatalogs = new ArrayList<Catalog>();
		for (int i = 0; i < 8; i++) {
			if (Disk.block[CDB][i + 5] == 8) {
				Catalog catalog = new Catalog(Disk.block[CDB], i * 8);
				dirCatalogs.add(catalog);
			}
		}
		return dirCatalogs;
	}
	
	public static List<Catalog> getFileCatalogs(int CDB) {
		List<Catalog> fileCatalogs = new ArrayList<Catalog>();
		for (int i = 0; i < 8; i++) {
			if (Disk.block[CDB][i + 5] == 4) {
				Catalog catalog = new Catalog(Disk.block[CDB], i * 8);
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
}
