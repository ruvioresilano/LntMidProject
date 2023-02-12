package Main;

import java.util.*;

public class Main {
	
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();
	ArrayList<Employee> karyList = new ArrayList<>();
	
	int inp;
	
	public Main() {
		do {
			
			mainMenu();
			
			switch (inp) {
			case 1:
				inputKary();
				
				break;
				
			case 2:
				tampilanKar(); 
				
				break;
				
			case 3:
				perbaruiKar();
				
				break;
				
			case 4:
				hapusKar();
				
				break;

			default:
				System.out.println("Berhasil keluar aplikasi!");
				break;
			}
			
		} while (inp !=5);
		
		
	}
	
	
	public void clear() {
		
		for (int i = 0; i < 50; i++) {
			System.out.println("");
		}
		
	}
	
	
	public String kodeKary() {
		String yoi = "";
		String word = "QWERTYUIOPASDFGHJKLZXCVBNM";
		
		for (int i = 0; i < 6; i++) {
			if (i == 2) {
				yoi += "-";
			}else if (i < 2) {
				yoi += word.charAt(rand.nextInt(26));
			}else {
				yoi += rand.nextInt(10);
			}
		}
		return yoi;
	}
	
	
	public void mainMenu() {
		
		System.out.println("=".repeat(33));
		System.out.println("PT Meksiko Data Karyawan\t|");
		System.out.println("=".repeat(33));
		System.out.println("1. Insert Data Karyawan\t\t|");
		System.out.println("2. Tampilkan Data Karyawan\t|");
		System.out.println("3. Perbarui Data Karyawan\t|");
		System.out.println("4. Hapus Data Karyawan\t\t|");
		System.out.println("5. Keluar Aplikasi\t\t|");
		System.out.println("=".repeat(33));
		
		do {
			try {
				System.out.print("Pilih Dan Masukan Angka [1-5]");
				System.out.print("\n>> ");
				inp = sc.nextInt(); sc.nextLine();
			} catch (Exception e) {
				System.out.println("Input Wajib Angka!");
				sc.nextLine();
			}
		} while (inp < 1 || inp > 5);
		
		
	}

	
	public void hitungGaji() {
		int wage;
		int man = 3, sup = 3, adm = 3;
		
		for (int i = 0; i < karyList.size(); i++) {
            Employee employee = karyList.get(i);
            if (employee.position.equals("Manager")) {
                if (getNumOfSamePosition(karyList, employee) >= man) {
                    employee.wage = (int) (employee.wage * 1.1);
                } man += 3;
                
            } else if (employee.position.equals("Supervisor")) {
                if (getNumOfSamePosition(karyList, employee) >= sup) {
                    employee.wage = (int) (employee.wage * 1.075);
                }sup += 3;
                
            } else if (employee.position.equals("Admin")) {
                if (getNumOfSamePosition(karyList, employee) >= 3) {
                    employee.wage = (int) (employee.wage * 1.05);
                }adm += 3;
            }
        }

   }
        
	
	private static int getNumOfSamePosition(ArrayList<Employee> karyList, Employee employee) {
		int count = 0;
		for (Employee emp : karyList) {
            if (emp.position.equals(employee.position)) {
                count++;
            }
        }
        return count;
    }
	


	public void inputKary() {
		
		String name, gender, position;
		String id = kodeKary();
		int wage = 0;
		
		clear();
	
		do {
			System.out.print("Input nama [lebih dari 3 karakter]: ");
			name = sc.nextLine();
			if (name.length() < 3) {
				System.out.println("Nama wajib lebih dari 3 karakter! ");
			}
		} while (name.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			gender = sc.nextLine();
			if (!(gender.equals("Laki-laki") || gender.equals("Perempuan"))) {
				System.out.println("Pilih antara [Laki-laki | Perempuan] (Case Sensitive)!");
			}
		} while (!(gender.equals("Laki-laki") || gender.equals("Perempuan")));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			position = sc.nextLine();
			if (!(position.equals("Manager") || position.equals("Supervisor") || position.equals("Admin"))) {
				System.out.println("Pilih antara [Manager | Supervisor | Admin] (Case Sensitive)!");
			}
		} while (!(position.equals("Manager") || position.equals("Supervisor") || position.equals("Admin")));
		
		if (position.equals("Manager")) {
			wage = 8000000;
		}else if (position.equals("Supervisor")) {
			wage = 6000000;
		}else if (position.equals("Admin")) {
			wage = 4000000;
		}
		
		
		
		System.out.println("Berhasi menambahkan karyawan dengan ID " + id);
		System.out.println("");
		System.out.println("Tekan enter untuk kembali ke menu utama..");
		sc.nextLine();
		
		Employee newEmpolyee = new Employee(id, name, gender, position, wage);
		karyList.add(newEmpolyee);
		
		clear();
		
	}
	
	
	public void sorting() {
		
		for (int i = 0; i < karyList.size(); i++) {
			for (int j = 0; j < karyList.size() -1 ; j++) {
				if (karyList.get(j).name.compareToIgnoreCase(karyList.get(j+1).name) > 0) {
					Employee temp = karyList.get(j);
					karyList.set(j, karyList.get(j + 1));
					karyList.set(j+1, temp);
				}
			}
		}	
	}
	
	
	public void tampilanKar() {

		clear();
		
		if (karyList.isEmpty()) {
			System.out.println("Tidak ada data karyawan!");
		}else {
			sorting();
			int nom = 1;
			
	        System.out.println("|----|-----------------|------------------------------|----------------|------------|----------------|");
	        System.out.println("| No | Kode karyawan   | Nama Karyawan                | Jenis Kelamin  | Jabatan    | Gaji Karyawan  |");
	        System.out.println("|----|-----------------|------------------------------|----------------|------------|----------------|");

	        for (Employee x : karyList) {
	            System.out.printf("| %2d | %15s | %28s | %14s | %10s | %14s |\n", nom, x.id, x.name, x.gender, x.position, x.wage);
	            nom++;
	        }
	        System.out.println("|----|-----------------|------------------------------|----------------|------------|----------------|");
	       
	    }
		
		
			
	}

	
	public void perbaruiKar() {
		
		String id = kodeKary();
		int newWage = 0;
		int update = -1;
		String newName, newGender = null, newPosition;
		String newId = kodeKary();
		
		clear();
		
		tampilanKar();
		if (karyList.isEmpty()) {
			mainMenu();
		}else {
			
			do {
				try {
					System.out.println();
					System.out.print("Input no urutan karyawan yang ingin diperbarui [1 - " + karyList.size() + "]: ");
					update = sc.nextInt(); sc.nextLine();
				} catch (Exception e) {
					System.out.println("Input wajib angka");
					sc.nextLine();
				}
			} while (update < 1 || update > karyList.size());
			
			
			do {
				System.out.print("Input nama [lebih dari 3 karakter]: ");
				newName = sc.nextLine();
				if (newName.length() < 3 && !newName.equals("0")) {
					System.out.println("Nama wajib lebih dari 3 karakter! ");
				} else if (newName.equals("0")) newName = karyList.get(update - 1).name;
			} while (newName.length() < 3);
			
			
			do {
				System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				newGender = sc.nextLine();
				if (!(newGender.equals("Laki-laki") || newGender.equals("Perempuan")) && !newGender.equals("0")) {
					System.out.println("Pilih antara [Laki-laki | Perempuan] (Case Sensitive)!");
				}  else if (newGender.equals("0")) newGender = karyList.get(update - 1).gender;
			} while (!(newGender.equals("Laki-laki") || newGender.equals("Perempuan")));
			
			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
				newPosition = sc.nextLine();
				if (!(newPosition.equals("Manager") || newPosition.equals("Supervisor") || newPosition.equals("Admin") || newPosition.equals("0"))) {
					System.out.println("Pilih antara [Manager | Supervisor | Admin] (Case Sensitive)!");
				}  else if (newPosition.equals("0")) newPosition = karyList.get(update - 1).position;
			} while (!(newPosition.equals("Manager") || newPosition.equals("Supervisor") || newPosition.equals("Admin")));
			
			System.out.println("Berhasi perbarui data karyawan dengan Id " + karyList.get(update -1).id);
			System.out.println("Tekan enter untuk kembali ke menu utama..");
			sc.nextLine();
			
			
			Employee newEmployee = new Employee(newId, newName, newGender, newPosition, newWage);
			karyList.set(update - 1, newEmployee);
			
		}
		
		
		clear();
		
		
	}
	
	
	public void hapusKar() {
		
		int delete = -1;
		
		clear();
		
		tampilanKar();
		if (karyList.isEmpty()) {
			mainMenu();
		}else {
			do {
				try {
					System.out.println();
					System.out.print("Input no urutan karyawan yang ingin dihapus [1 - " + karyList.size() + "]: ");
					delete = sc.nextInt(); sc.nextLine();
				} catch (Exception e) {
					System.out.println("Input wajib angka");
					sc.nextLine();
				}
			} while (delete < 1 || delete > karyList.size());
			
			System.out.println("Karyawan dengan kode " + karyList.get(delete -1).id + " berhasil dihapus");
			System.out.println("Tekan enter untuk kembali ke menu utama..");
			sc.nextLine();
			
			karyList.remove(delete - 1);
		}
		
		
		clear();
	}
	
	
	public static void main(String[] args) {
		new Main();
	}

}
