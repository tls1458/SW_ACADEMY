-- ROWNUM : Sudo Column
-- Q. salary(급여)가 작은 3명을 조회  
SELECT e.employee_id, d.department_id, d.department_name, e.first_name, e.last_name, e.hire_date
FROM employees e, departments d
WHERE d.department_id = e.department_id
GROUP BY e.employee_id, d.department_id, d.department_name
HAVING  min(e.hire_date);

SELECT e.employee_id, d.department_id, d.department_name, e.first_name, e.last_name, e.hire_date
FROM employees e, departments d
WHERE d.department_id = e.department_id
GROUP BY e.employee_id, d.department_id, d.department_name, e.first_name, e.last_name, e.hire_date

;

-- TOP QUERY
SELECT *
FROM (SELECT employee_id, salary, last_name
        FROM employees
        ORDER BY salary) sal_emp
WHERE ROWNUM<4;

-- 최근 입사일자 세사람 조회 
SELECT *
FROM (SELECT employee_id,
-- 도시별 부서개수 많은 순서 2개 도시 출