package myjdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {
   static Scanner scan = new Scanner(System.in);

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      staticEmp semp = new staticEmp();
      System.out.println("직원관리정보시스템입니다.");
      System.out.println();
      while (true) {
         System.out.println("1. 검색 2. 통계자료 3. 프로그램 종료");
         System.out.print("입력 : ");
         String select1 = scan.nextLine();
         switch (select1) {
         case "1":// 검색
            EmpSelect();
            break;

         case ("2"):// 통계자료
            try {
               semp.statistics();
            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            break;
         case ("3"):// 프로그램종료
            System.out.println("프로그램종료");
            System.exit(0);
            break;
         default:
            //
         }
      }

   }

   static void EmpSelect() {
      int run = 1;
      EmpDAO empDAO = new EmpDAO();
      while (run == 1) {
         System.out.println("1. 사용자 이름 2. 도시 이름 3. 입사년도 4. 부서장 이름 5. 부서 번호 6. 처음화면이동 7. 프로그램종료");
         System.out.print("입력 : ");
         String select = scan.nextLine();
         switch (select) {
         case ("1"):// 사용자 이름
            System.out.print("이름을 입력하세요. : ");
            String firstName = scan.nextLine();
            System.out.print("성을 입력하세요. : ");
            String lastName = scan.nextLine();
            try {
               empDAO.getEmpByName(firstName, lastName);
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            break;
         case ("2"):// 도시 이름
            System.out.print("이름을 입력하세요. : ");
            String city = scan.nextLine();
            try {
               empDAO.getEmpListByCityName(city);
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            break;
         case ("3"):// 입사년도
            System.out.print("입사년도을 입력하세요. : ");
            int hireYear = Integer.parseInt(scan.nextLine());
            try {
               empDAO.getEmpListByHireDate(hireYear);
            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            break;
         case ("4"):// 부서장 이름
            System.out.print("부서장 이름 입력하세요. : ");
            firstName = scan.nextLine();
            System.out.print("부서장 성을 입력하세요. : ");
            lastName = scan.nextLine();
            try {
               empDAO.getEmpListByManagerName(firstName, lastName);
            } catch (Exception e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            break;
         case "5":// 부서번호
            System.out.print("부서번호를 입력하세요. : ");
            int deptId = Integer.parseInt(scan.nextLine());
            try {
               empDAO.getEmpByDeptId(deptId);
            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            break;

         case ("6"):// 처음화면 이동
            run = 0;
            break;
         case ("7"):// 프로그램 종료
            System.out.println("프로그램종료");
            System.exit(0);
            break;
         default://
         }
      }
   }

}

class EmpDAO {
   DecimalFormat formatter = new DecimalFormat("###,###");

   public void getEmpByName(String firstName, String lastName) throws Exception {

      EmpVO result = new EmpVO();
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe", "hr", "hr");
//      
      String sql = "select  substr(e.HIRE_DATE,1),e.*  FROM EMPLOYEES e where FIRST_NAME = ? and LAST_NAME = ? order BY EMPLOYEE_ID";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, firstName);
      stmt.setString(2, lastName);
      ResultSet rs = stmt.executeQuery();
      int cnt = 0;
      System.out.printf("%s\t/%11s /%9s /%s\t/%9s / %s\n", EmpVO.nameTag[6], EmpVO.nameTag[7], EmpVO.nameTag[8],
            EmpVO.nameTag[0], EmpVO.nameTag[9], EmpVO.nameTag[10]);
      while (rs.next()) {
         result.empId = rs.getInt("EMPLOYEE_ID");
         result.firstName = rs.getString("FIRST_NAME");
         result.lastName = rs.getString("LAST_NAME");
         result.salary = rs.getInt("SALARY");
         result.hireDate = rs.getString(1);
         result.deptId = rs.getInt("DEPARTMENT_ID");

         System.out.printf("%d\t/%11s /%10s /%d\t/%10s / $ %s\n", result.empId, result.firstName, result.lastName,
               result.deptId, result.hireDate, formatter.format(result.salary));

      }
      System.out.println();
   }

   public void getEmpListByHireDate(int hireYear) throws Exception {
      ArrayList<EmpVO> results = new ArrayList<EmpVO>();

      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe", "hr", "hr");
      String sql = "SELECT substr(e.HIRE_DATE,1),e.*  FROM EMPLOYEES e where EXTRACT(year from HIRE_DATE) = ?";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, hireYear);
      ResultSet rs = stmt.executeQuery();

      System.out.printf("%s\t/%11s /%9s /%s\t/%9s / %s\n", EmpVO.nameTag[6], EmpVO.nameTag[7], EmpVO.nameTag[8],
            EmpVO.nameTag[0], EmpVO.nameTag[9], EmpVO.nameTag[10]);
      while (rs.next()) {
         EmpVO result = new EmpVO();

         result.empId = rs.getInt("EMPLOYEE_ID");
         result.firstName = rs.getString("FIRST_NAME");
         result.lastName = rs.getString("LAST_NAME");
         result.salary = rs.getInt("SALARY");
         result.hireDate = rs.getString(1);
         result.deptId = rs.getInt("DEPARTMENT_ID");

         results.add(result);
         System.out.printf("%d\t/%11s /%10s /%d\t/%10s / $ %s\n", result.empId, result.firstName, result.lastName,
               result.deptId, result.hireDate, formatter.format(result.salary));
      }
      System.out.println();

   }

   public void getEmpByDeptId(int deptId) throws Exception {
      ArrayList<EmpVO> list = new ArrayList<EmpVO>();
      Class.forName("oracle.jdbc.driver.OracleDriver");
//         Calendar cal = Calendar.getInstance();  
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe", "hr", "hr");
      String sql = "select substr(e.HIRE_DATE,1),e.*  FROM EMPLOYEES e where department_id = ?";
//         String sql1 = "select to_char(salary, '999,999,999') from employees";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, deptId);
      ResultSet rs = stmt.executeQuery();
      System.out.printf("%s\t/%11s /%9s /%s\t/%9s / %s\n", EmpVO.nameTag[6], EmpVO.nameTag[7], EmpVO.nameTag[8],
            EmpVO.nameTag[0], EmpVO.nameTag[9], EmpVO.nameTag[10]);
      while (rs.next()) {
         EmpVO result = new EmpVO();
         result.empId = rs.getInt("EMPLOYEE_ID");
         result.firstName = rs.getString("FIRST_NAME");
         result.lastName = rs.getString("LAST_NAME");
         result.salary = rs.getInt("SALARY");
         result.hireDate = rs.getString(1);
         result.deptId = rs.getInt("DEPARTMENT_ID");
//            System.out.println(result.toString());
         System.out.printf("%d\t/%11s /%10s /%d\t/%10s / $ %s\n", result.empId, result.firstName, result.lastName,
               result.deptId, result.hireDate, formatter.format(result.salary));

         list.add(result);

      }
      System.out.println();
   }

   public void getEmpListByCityName(String city) throws Exception {
      ArrayList<EmpVO> list = new ArrayList<EmpVO>();
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe", "hr", "hr");

      String sql = "SELECT substr(e.HIRE_DATE,1),e.*  FROM EMPLOYEES e, DEPARTMENTS d, locations l\r\n"
            + "WHERE d.location_id = l.location_id\r\n" + "AND e.DEPARTMENT_ID = d.DEPARTMENT_ID\r\n"
            + "AND l.city = ?";

      PreparedStatement pre = con.prepareStatement(sql);
      pre.setString(1, city);
      ResultSet rs = pre.executeQuery();

      System.out.printf("%s\t/%11s /%9s /%s\t/%9s / %s\n", EmpVO.nameTag[6], EmpVO.nameTag[7], EmpVO.nameTag[8],
            EmpVO.nameTag[0], EmpVO.nameTag[9], EmpVO.nameTag[10]);

      while (rs.next()) {

         EmpVO empvo = new EmpVO();
         empvo.empId = rs.getInt("EMPLOYEE_ID");
         empvo.firstName = rs.getString("FIRST_NAME");
         empvo.lastName = rs.getString("LAST_NAME");
         empvo.salary = rs.getInt("SALARY");
         empvo.hireDate = rs.getString(1);
         empvo.deptId = rs.getInt("DEPARTMENT_ID");

         list.add(empvo);
         System.out.printf("%d\t/%11s /%10s /%d\t/%10s / $ %s\n", empvo.empId, empvo.firstName, empvo.lastName,
               empvo.deptId, empvo.hireDate, formatter.format(empvo.salary));
      }

      rs.close();
      System.out.println();

   }

   public void getEmpListByManagerName(String fName, String lName) throws Exception {
      ArrayList<EmpVO> results = new ArrayList<EmpVO>();

      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe", "hr", "hr");
//      
      String sql = "select substr(e.HIRE_DATE,1),e.* from EMPLOYEES e where MANAGER_ID = (select EMPLOYEE_ID from EMPLOYEES where  FIRST_NAME = ? and LAST_NAME = ?) order BY EMPLOYEE_ID";
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, fName);
      stmt.setString(2, lName);
      ResultSet rs = stmt.executeQuery();
      int cnt = 0;

      System.out.printf("%s\t/%11s /%9s /%s\t/%9s /%s/ %s\n", EmpVO.nameTag[6], EmpVO.nameTag[7], EmpVO.nameTag[8],
            EmpVO.nameTag[0], EmpVO.nameTag[9],"부서장 사원번호", EmpVO.nameTag[10]);

      while (rs.next()) {
         EmpVO result = new EmpVO();
         result.empId = rs.getInt("EMPLOYEE_ID");
         result.firstName = rs.getString("FIRST_NAME");
         result.lastName = rs.getString("LAST_NAME");
         result.salary = rs.getInt("SALARY");
         result.hireDate = rs.getString(1);
         result.deptId = rs.getInt("DEPARTMENT_ID");
         int managerId = rs.getInt("MANAGER_ID");
         results.add(result);
         System.out.printf("%d\t/%11s /%10s /%d\t/%10s /%d\t/ $ %s\n", result.empId, result.firstName, result.lastName,
               result.deptId, result.hireDate, managerId, formatter.format(result.salary));

      }
      System.out.println();
   }
}

class staticEmp {
   public void statistics() throws Exception {
      Connection con = null;
      Class.forName("oracle.jdbc.driver.OracleDriver"); // JDBC 드라이버 로딩
      String url = "jdbc:oracle:thin:@localhost:49161:xe"; // 연결 문자열, mac 이기 떄문에 49161, xe
      String id = "hr"; // 데이터베이스 ID
      String pw = "hr"; // 데이터베이스 PW
      con = DriverManager.getConnection(url, id, pw); // Connection 생성, 데이터베이스 연결, return 타입 : DriverManager

      Scanner scan = new Scanner(System.in);
      Scanner scan2 = new Scanner(System.in);

      DecimalFormat formatter = new DecimalFormat("###,###");

      String sql = null;
      PreparedStatement stmt = null;

      int run = 1;

      int deptnum = -1;

      while (run == 1) {
         System.out.printf("1. 최대/최소급여 \t 2. 평균급여 \t 3.부서에 해당하는 인원 \t 4. 처음화면이동 \t 5. 프로그램종료\n");
         System.out.printf("입력 : ");
         int num = scan.nextInt();

         if (num == 5) {
            System.out.println("프로그램종료!");
            System.exit(0);
         } else if (num == 4) {
            run = 0;
            break;
         }

         System.out.printf("부서 번호 또는 부서 이름을 검색하세요 (모든 정보를 원하면, 0 입력) : ");
         String input_dept = scan2.nextLine();
         
         if (isNmber(input_dept))
            deptnum = Integer.parseInt(input_dept);

         System.out.println();
         
         switch (num) {
         case 1: // 1. 최대/최소급여
            if (deptnum > 0) { // 부서번호로 입력했으면,
               sql = "SELECT d.department_id, d.department_name, MAX(salary) as max, MIN(salary) as min\n"
                     + "FROM employees e, departments d\n"
                     + "WHERE e.department_id = d.department_id AND e.department_id = ? \n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);
               stmt.setInt(1, deptnum);
            }

            else if (deptnum == -1) { // 부서이름으로 입력하면,
               sql = "SELECT d.department_id, d.department_name, MAX(salary) as max, MIN(salary) as min\n"
                     + "FROM employees e, departments d\n"
                     + "WHERE e.department_id = d.department_id AND d.department_name = ? \n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);
               stmt.setString(1, input_dept);
            }

            else { // 0을 입력하면 전체정보 저장
               sql = "SELECT d.department_id, d.department_name, MAX(salary) as max, MIN(salary) as min\n"
                     + "FROM employees e, departments d\n" + "WHERE e.department_id = d.department_id \n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);

            }

            ResultSet rs = stmt.executeQuery();

            System.out.printf("%s\t/%15s / %s\t/ %s\n", EmpVO.nameTag[0], EmpVO.nameTag[1], EmpVO.nameTag[2],
                  EmpVO.nameTag[3]);

            while (rs.next()) {
               System.out.printf("%d\t/%16s / $ %s\t/ $ %s \n", rs.getInt("department_id"),
                     rs.getString("department_name"), formatter.format(rs.getInt("max")),
                     formatter.format(rs.getInt("min")));

            }
            deptnum = -1;
            break;

         case 2: // 2. 평균급여
            if (deptnum > 0) {
               sql = "SELECT d.department_id, d.department_name, ROUND(AVG(salary)) as avg\n"
                     + "FROM employees e, departments d\n"
                     + "WHERE e.department_id = d.department_id AND d.department_id = ? \n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);
               stmt.setInt(1, deptnum);

            } else if (deptnum == -1) {
               sql = "SELECT d.department_id, d.department_name, ROUND(AVG(salary)) as avg\n"
                     + "FROM employees e, departments d\n"
                     + "WHERE e.department_id = d.department_id AND d.department_name = ? \n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);
               stmt.setString(1, input_dept);

            }

            else {
               sql = "SELECT d.department_id, d.department_name, ROUND(AVG(salary)) as avg\n"
                     + "FROM employees e, departments d\n" + "WHERE e.department_id = d.department_id\n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);

            }

            rs = stmt.executeQuery();

            System.out.printf("%s\t/%15s / %s \n", EmpVO.nameTag[0], EmpVO.nameTag[1], EmpVO.nameTag[4]);

            while (rs.next()) {
               System.out.printf("%d\t/%16s / $ %s \n", rs.getInt("department_id"),
                     rs.getString("department_name"), formatter.format(rs.getInt("avg")));

            }
            deptnum = -1;
            break;

         case 3: // 3. 부서에 해당하는 인원수
            if (deptnum > 0) {
               sql = "SELECT d.department_id, d.department_name, count(e.employee_id) as count\n"
                     + "FROM employees e, departments d\n"
                     + "WHERE e.department_id = d.department_id AND d.department_id = ? \n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);
               stmt.setInt(1, deptnum);

            } else if (deptnum == -1) {
               sql = "SELECT d.department_id, d.department_name, count(e.employee_id) as count\n"
                     + "FROM employees e, departments d\n"
                     + "WHERE e.department_id = d.department_id AND d.department_name = ? \n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);
               stmt.setString(1, input_dept);
            } else {
               sql = "SELECT d.department_id, d.department_name, count(e.employee_id) as count\n"
                     + "FROM employees e, departments d\n" + "WHERE e.department_id = d.department_id\n"
                     + "GROUP BY d.department_id, d.department_name\n" + "ORDER BY d.department_id";
               stmt = con.prepareStatement(sql);
            }

            rs = stmt.executeQuery();
            System.out.println("부서번호 / 부서이름 / 인원(명)");
            System.out.printf("%s\t/%15s / %s\n", EmpVO.nameTag[0], EmpVO.nameTag[1], EmpVO.nameTag[5]);

            while (rs.next()) {
               System.out.printf("%d\t/%16s / %4d명\n", rs.getInt("department_id"), rs.getString("department_name"),
                     rs.getInt("count"));

            }

            deptnum = -1;
            break;
         }

         System.out.println("");
      }
   }

   // 문자인지 숫자인지 구분
   public static boolean isNmber(String s) {

      try {
         Integer.parseInt(s);
         return true;
      } catch (Exception e) {
         return false;
      }

   }

}

class EmpVO {
   public int empId;
   public String firstName;
   public String lastName;
   public int salary;
   public String hireDate;
   public int deptId;

   static String[] nameTag = new String[] { "부서번호", "부서이름", "최대급여", "최소급여", "평균급여", "인원(명)", "사원번호", "성", "명", "입사일자",
         "급여" };

   @Override
   public String toString() {
      return "EmpVO [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
            + ", hireDate=" + hireDate + "]";
   }

}