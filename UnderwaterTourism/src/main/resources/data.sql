/*ALL PASSWORDS ARE: 123*/

INSERT INTO `roles` (id,role) 
VALUES
(1,'ADMIN'), 
(2,'USER'), 
(3,'EMPLOYEE'),
(4,'MANAGER');

INSERT INTO `users` (id, age, email, first_name, gender, last_name, password, phone) 
VALUES 
(	
	1,
	30,
	'admin@admin.bg',
	'Admin',
	'Male',
	'Adminov',			 
	'23f442f5f13716173e5a2dff4b90be66d9f462cccd25eeb8a7bb227622aab4d3ee5a2b4d4db0b6af',
	'0898123321'
),
(
	2,
	25,
    'poseidon@gbg.bg',
    'Poseidon',
    'Male',
    'Poseidonov',
    '23f442f5f13716173e5a2dff4b90be66d9f462cccd25eeb8a7bb227622aab4d3ee5a2b4d4db0b6af',
    '0899321222'
),
(
	3,
	40,
	'namorita@gbg.bg',
    'Namorita',
    'Female',
    'Namori',
    '23f442f5f13716173e5a2dff4b90be66d9f462cccd25eeb8a7bb227622aab4d3ee5a2b4d4db0b6af',
    '0877475009'
),
(
	4,
	22,
	'pesho@gbg.bg',
    'Pesho',
    'Male',
    'Peshev',
    '23f442f5f13716173e5a2dff4b90be66d9f462cccd25eeb8a7bb227622aab4d3ee5a2b4d4db0b6af',
    '0877475009'
);
            

INSERT INTO `users_roles` (user_id, role_id)
VALUES 
(1,1), 
(2,2), (2,4),
(3,2), (3,3),
(4,2);

INSERT INTO `employees` (id, birth_date, hire_date, job_title, salary, user_id)
VALUES
(
	1,
	'1999-01-01',
	'2018-01-01',
	'Admin',
	1500,
	1
),
(
	2,
	'2000-01-01',
	'2019-01-01',
	'Diver Manager',
	2000,
	2
),
(
	3,
	'2001-01-01',
	'2020-01-01',
	'Shark Cage Diver',
	600,
	3
);

INSERT INTO `departments`(id,name)
VALUES
(
1,
'Diving'
),
(
2,
'Restaurant'
);

INSERT INTO `attractions`(id, title, description, price, duration, image, diving_season, difficulty,department_id)
VALUES
(
1,
'Shark Cage Dive',
'The great white season runs from July to November, with numerous lively young males arriving ready to mate early in the season,and the larger males making an appearance from late September onwards. The largest females arrive well into October when the weather is starting to turn,and many consider this the ideal time to visit.',
100,
1,
'shark_cage.jpg',
'July to November.',
'Suitable for all levels, including non-divers.',
1
),
(
2,
'Undersea Restaurant',
'The world''s largest all-glass undersea restaurant.You can sate your appetite and dive into the pleasure of having a view that but a few restaurants in the world afford.',
100,
1,
'restaurant.jpg',
'All the time.',
'None',
2
);


INSERT INTO `reports`(id,from_date,to_date,income)
VALUES
(
1,
'2022-07-20',
'2022-08-10',
100
);

 