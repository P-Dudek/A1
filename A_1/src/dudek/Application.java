package dudek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {

	public static String readFileHex(String path) {
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(path));
			int c;
			while ((c = bufferedReader.read()) != -1) {
				char a = (char) c;
				if (Character.toString(a).matches("[0-9A-Fa-f]")) {
					sb.append((char) c);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}

	public static byte[] stringhextoByteArray(String s) {
		int leng = s.length();
		byte[] data = new byte[leng / 2];
		leng=leng/2;leng=leng*2;
		for (int i = 0; i < leng; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static void saveText(List<Byte> list, List<Byte> list2) {
		BufferedWriter output = null;
		String path = "src/dudek/output.txt";
		try {
			output = new BufferedWriter(new FileWriter(path));
			output.write("EVEN:");
			for (Byte b : list) {
				output.newLine();
				output.write("byte: " + b + ", hex: " + String.format("%02X ", b));
			}
			output.newLine();
			output.write("ODD:");
			for (Byte b : list2) {
				output.newLine();
				output.write("byte: " + b + ", hex: " + String.format("%02X ", b));
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void divide(byte[] b) {
		List<Byte> even = new ArrayList<Byte>();
		List<Byte> odd = new ArrayList<Byte>();
		for (int i = 0; i < b.length; i++) {
			if (b[i] % 2 == 0) {
				even.add(b[i]);
			} else {
				odd.add(b[i]);
			}
		}
		Collections.sort(even, new CustomComparator());
		Collections.sort(odd, Collections.reverseOrder(new CustomComparator()));
		saveText(even, odd);
	}

	public static void main(String[] args) {
		String data = readFileHex("src/dudek/input.txt");
		byte[] arr = stringhextoByteArray(data);
		divide(arr);
	}
}
