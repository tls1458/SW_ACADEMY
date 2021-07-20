-- Neena가 근무하는 부서의 부서원 정보를 출력하시오 .
-- Neena가 근무

SELECT department_id
FROM employees
WHERE first_name = 'Neena';

-- 90번 사원
SELECT *
FROM employees
WHERE department_id = 90;


-- SubQuery : 쿼리속의 쿼리 
-- 이름이 Neena와 부서ID와 같은 직원들의 정보 출력
SELECT *
FROM employees
WHERE department_id = (SELECT department_id
                        FROM employees
                        WHERE first_name = 'Neena');

-- Steven King보다 급여가 높은 직원들의 정보를 출력
SELECT *
FROM employees
WHERE salary > (SELECT salary FROM employees WHERE first_name = 'Steven' AND last_name = 'King');

-- any :  서브쿼리를 실행 했을 때 
SELECT *
FROM employees
WHERE salary > any (SELECT salary FROM employees WHERE last_name = 'King');

-- Seattle에 근무하는 사원들의 정보를 출력 (단 서브쿼리로)
SELECT *
FROM employees
WHERE department_id IN (SELECT department_id FROM departments
WHERE location_id = (SELECT location_id FROM Locations WHERE city = 'Seattle'));

-- Collated Query상호연관서브쿼리
SELECT *
FROM employees e
WHERE salary > (select avg(salary) FROM  employees WHERE department_id = e.department_id); -- 자신의 

-- 각 부서별로 
SELECT department_id, employee_id, salary
FROM employees
WHERE (department_id, salary) IN (SELECT department_id, MAX(salary) FROM employees GROUP BY department_id)
ORDER BY salary;

-- 
SELECT e.department_id, e.first_name, e.salary
FROM employees e, (SELECT department_id, MAX(salary) max_salary FROM employees GROUP BY department_id) dept_max_sal
WHERE e.department_id = dept_max_sal.department_id
AND e.salary = dept_max_sal.max_salary
;


-- 
SELECT employee_id, salary, (SELECT AVG(salary) FROM employees WHERE employess.department_id = departments.department_id) avg_salaray
FROM employees;

