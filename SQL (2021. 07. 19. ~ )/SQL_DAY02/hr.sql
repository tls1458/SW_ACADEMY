SELECT DISTINCT job_id FROM employees;


-- IT_PRGO이라면 급여의 20%를 인상한 결과를 출력
-- SH_CLERK 이면 급여의 50% 인상하여 출력
-- 나머지 급여를 출력하시오
-- 1) DECODE
SELECT employee_id, job_id, salary,
    DECODE(job_id, 'IT_PROG', salary*1.2, 'SH_CLERK', salary*1.5, salary) revised_salary
FROM employees;

-- 2) CASE인
SELECT employee_id, job_id, salary,
    CASE job_id WHEN 'IT_PROG' THEN salary * 1.2
                WHEN 'SH_CLERK' THEN salary * 1.5
                ELSE salary
    END revised_salary
FROM employees;

-- 도전과제 (강사님과 함께했음)
/*
사번, 이름, 이름, 입사일자, 급여등급을 출력하세요.
15000 이상이면 S, 10000 이상이면 A, 7000이상이면 B, 7000미만이면 C 출력 
*/

SELECT employee_id 사번, first_name 이름, last_name 성, hire_date 입사일자, salary 급여,
    CASE    WHEN salary >= 15000 THEN 'S'
            WHEN salary >= 10000 AND salary < 15000 THEN 'A'
            WHEN salary >= 7000 AND salary < 10000 THEN 'B'
            ELSE 'C'
    END AS "급여등급" 
FROM employees;

-- join
-- 사번, 이름 근무부서명을 출력하시오.
-- 어떤 테이블, 어떤 컬럼에서 조회할 것인가 ?
SELECT COUNT(*) -- employee_id, first_name, last_name, department_name
FROM employees, departments;

-- 전체 ROW 개수(카디널리티) 출력
SELECT COUNT(*) FROM employees;  -- 107
SELECT COUNT(*) FROM departments;

-- 스티븐 킹 Steven, King의 정보를 조회하고자 한다.
SELECT * FROM employees WHERE first_name='Steven' AND last_name='King';

-- 스티븐 킹이 근무하는 부서의 이름을 출력
SELECT department_name FROM departments, employees WHERE departments.department_id=employees.department_id AND (employees.first_name='Steven' AND employees.last_name='King');

SELECT count(*) FROM departments, employees WHERE departments.department_id=employees.department_id ; -- 결과 : 106

-- 오류 발생 !! 원인 : 동일한 열 이름이 조인되는 개별 테이블 둘 이상에 존재합니다. 사용자 지정 SQL에 사용할 열을 결정할 수 있는 충분한 정보가 포함되어 있지 않습니다. 
SELECT department_id , employee_id, first_name, last_name, department_name FROM departments, employees WHERE departments.department_id=employees.department_id;

SELECT e.department_id, e.employee_id, e.first_name, e.last_name, d.department_name
FROM departments d JOIN employees e
ON d.department_id = e.department_id; -- AND e.employee_id = 178;

SELECT COUNT(*) FROM employees;

-- 아직 부서에 배치되지 않은 사원 조회
-- department_id 에 값이 없는 경우를 조회
SELECT * FROM employees WHERE department_id IS NULL;

-- OUTER JOIN
SELECT * 
FROM employees e, departments d
WHERE e.department_id = d.department_id(+) AND e.employee_id = 178; 

-- LEFT OUTER JOIN
SELECT * 
FROM employees e
LEFT JOIN departments d on e.department_id = d.department_id 
WHERE e.employee_id=178 ;

-- SELF JOIN
-- 사번, 이름, 관리자, 사번, 급여 출력, 관리자 이름 
SELECT e.employee_id, e.first_name, e.last_name, e.manager_id, e.salary, m.first_name
FROM employees e, employees m
WHERE m.employee_id(+) = e.manager_id;

-- Neena의 관리자 번호 출력 
SELECT manager_id
FROM employees
WHERE fisrt_name = 'Neena';
-- 100번 사번의 이름을 출력하면
SELECT first_name, last_name FROM employees WHERE employee_id=100;

SELECT trunc(1520, -3)FROM DUAL;


-- GROUP BY
SELECT ROUND(AVG(salary)), department_id 
FROM employees
GROUP BY department_id
ORDER BY 2;

-- 전체 직원 대상
SELECT COUNT(*), AVG(salary), MAX(salary), MIN(salary)
FROM employees;

-- GROUP BY 
SELECT e.department_id, AVG(salary), d.department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id
GROUP BY e.department_id, department_name;

-- 평균급여가 5000이상인 부서별 평균급여를 출력하시오.
-- 부서별 평균급여를 출력(부서번호, 평균급여)
SELECT e.department_id 부서번호, AVG(salary) 평균급여 
FROM employees e
-- 오류 WHERE avg(salary)>=5000
GROUP BY e.department_id
HAVING AVG(salary)>=5000;

-- 각 부서의 부서평균급여
-- 여러 직급이 있다는데 프로그래머 선적담당 영업대표 회계사
SELECT job_id, AVG(salary)
FROM employees
GROUP BY job_id;
