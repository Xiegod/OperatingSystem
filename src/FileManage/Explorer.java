package FileManage;

import java.util.ArrayList;
import java.util.List;

public class Explorer {
	private static int CDB;
	private static List<Integer> PDB;
	public Explorer() {
		this.CDB = 2;
		PDB = new ArrayList<Integer>();
	}
	
	public static int getCDB() {
		return CDB;
	}
	
	public static void setCDB(int cDB) {
		CDB = cDB;
	}
	
	public static int popPDB() {
		int result = 2;
		if(PDB.size() != 0){
			result = PDB.get(PDB.size() - 1);
			PDB.remove(PDB.size() - 1);
		}
		return result;
	}
	
	public static void setPDB(List<Integer> pdb){
		PDB = pdb;
	}
	
	public static void addCDB() {
		PDB.add(CDB);
	}	
	
	public static void setToTop(){
		CDB = 2;
		PDB = new ArrayList<Integer>();
	}
	
	public static List<Integer> getPDB(){
		return PDB;	
	}
}
