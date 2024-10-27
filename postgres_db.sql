create table employees_techs
(
    employee_id integer not null,
    tech_id     integer not null
);

alter table employees_techs
    owner to ivcho;

INSERT INTO employees_techs (employee_id, tech_id) VALUES (5, 7);
INSERT INTO employees_techs (employee_id, tech_id) VALUES (5, 9);
INSERT INTO employees_techs (employee_id, tech_id) VALUES (4, 8);
INSERT INTO employees_techs (employee_id, tech_id) VALUES (4, 11);
INSERT INTO employees_techs (employee_id, tech_id) VALUES (7, 7);

create table employees
(
    id            serial
        primary key,
    first_name    varchar(60)  not null,
    middle_name   varchar(60)  not null,
    last_name     varchar(60)  not null,
    email         varchar(255) not null,
    domain_user   varchar(255) not null,
    computer_name varchar(255) not null,
    phone         varchar(255) not null,
    position      varchar(255) not null
);

alter table employees
    owner to ivcho;

INSERT INTO employees (id, first_name, middle_name, last_name, email, domain_user, computer_name, phone, position) VALUES (7, 'Ivian', 'Plamenov', 'Zafirov', 'ivianz1998@gmail.com', 'a1.bg', 'MAC', '0876455596', 'Sales');
INSERT INTO employees (id, first_name, middle_name, last_name, email, domain_user, computer_name, phone, position) VALUES (5, 'Test', 'Test', 'test', 'test@gmail.com', 'domain.com', 'PC', '08888888', 'Manager');
INSERT INTO employees (id, first_name, middle_name, last_name, email, domain_user, computer_name, phone, position) VALUES (4, 'Proba', 'proba', 'Proba', 'proba@a1.bg', 'google.com', 'PC', '0888888888', 'IT');

create table projects
(
    id         serial
        primary key,
    title      varchar(255) not null,
    created_at timestamp
);

alter table projects
    owner to ivcho;

INSERT INTO projects (id, title, created_at) VALUES (1, 'IT BootCamp 2024', '2024-08-27 16:04:43.326141');
INSERT INTO projects (id, title, created_at) VALUES (2, 'IT BootCamp 2025', '2024-08-27 17:13:06.890614');
INSERT INTO projects (id, title, created_at) VALUES (3, 'IT BootCamp 2026', '2024-08-27 17:13:10.279538');
INSERT INTO projects (id, title, created_at) VALUES (4, 'IT BootCamp 2027', '2024-08-27 17:13:13.896367');
INSERT INTO projects (id, title, created_at) VALUES (5, 'IT BootCamp 2028', '2024-08-27 17:13:18.674774');
INSERT INTO projects (id, title, created_at) VALUES (6, 'IT BootCamp 2029', '2024-08-27 17:13:22.267791');
INSERT INTO projects (id, title, created_at) VALUES (7, 'IT BootCamp 2030', '2024-08-27 17:13:25.993082');

create table techs
(
    id         serial
        primary key,
    title      varchar(255) not null,
    created_at timestamp
);

alter table techs
    owner to ivcho;

INSERT INTO techs (id, title, created_at) VALUES (5, 'CSS', '2024-08-27 17:03:03.289884');
INSERT INTO techs (id, title, created_at) VALUES (6, 'HTML', '2024-08-27 17:03:07.595847');
INSERT INTO techs (id, title, created_at) VALUES (7, 'JS', '2024-08-27 17:03:13.660613');
INSERT INTO techs (id, title, created_at) VALUES (8, 'Ruby', '2024-08-27 17:03:17.099997');
INSERT INTO techs (id, title, created_at) VALUES (9, 'Ruby On Rails', '2024-08-27 17:03:26.383603');
INSERT INTO techs (id, title, created_at) VALUES (10, 'C#', '2024-08-27 17:03:30.028270');
INSERT INTO techs (id, title, created_at) VALUES (2, 'JAVA', '2024-08-27 16:38:47.705614');
INSERT INTO techs (id, title, created_at) VALUES (1, 'PHP', '2024-08-27 16:38:43.032979');
INSERT INTO techs (id, title, created_at) VALUES (11, 'C++', '2024-08-27 17:03:42.802154');
INSERT INTO techs (id, title, created_at) VALUES (12, 'C', '2024-08-27 17:03:45.073981');
INSERT INTO techs (id, title, created_at) VALUES (13, 'Go', '2024-08-27 17:03:49.698046');

create table employees_projects
(
    employee_id integer not null,
    project_id  integer not null
);

alter table employees_projects
    owner to ivcho;

INSERT INTO employees_projects (employee_id, project_id) VALUES (5, 1);
INSERT INTO employees_projects (employee_id, project_id) VALUES (5, 2);
INSERT INTO employees_projects (employee_id, project_id) VALUES (5, 3);
INSERT INTO employees_projects (employee_id, project_id) VALUES (4, 1);
INSERT INTO employees_projects (employee_id, project_id) VALUES (4, 5);
INSERT INTO employees_projects (employee_id, project_id) VALUES (7, 2);
