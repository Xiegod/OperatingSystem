package FileManage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.LoaderHandler;

public class Disk{
	
	static String DFPath = "data\\disk.dat";
	public byte[][] block;
	public static File dataFile;
	
	public Disk() throws IOException{
		dataFile = new File(DFPath);
		if (!dataFile.exists()) {
			System.out.println("�����ļ������ڣ�");
		}	
		
		//----init()����һ��ʹ�ñ������ʼ����----
//		init();
		
		load();
	}
	
	public void load() throws IOException{
		block = new byte[128][64];
		FileInputStream inputStream = new FileInputStream(dataFile);
		for (int i = 0; i < this.block.length; i++) {
			if (inputStream.read(block[i],0,64) == 64) {
//			System.out.println("�� "+ i + " ���̿��ȡ�ɹ�");
			}else {
				System.out.println("�� "+ i + " ���̿��ȡʧ�ܣ�������");
			}
		}
		inputStream.close();
	}
	
	
	public void init() throws IOException{
		byte[] FAT = new byte[128];
		byte[][] block = new byte[126][64];
		for (int i = 0; i < 126; i++) {
			for (int j = 0; j < 64; j++) {
				block[i][j] = (byte)-1;
			}
		}
		
		FAT[0] = FAT[1] = FAT[2] = (byte)255;
		
		FileOutputStream outputStream = new FileOutputStream(dataFile);
		outputStream.write(FAT);
		for (int i = 0; i < block.length; i++) {
			outputStream.write(block[i],0,64);
		}
		
		outputStream.close();
	}
}
