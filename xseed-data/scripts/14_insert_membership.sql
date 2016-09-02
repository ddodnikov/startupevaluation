INSERT INTO memberships(role, startup_id, user_id) VALUES
('ADMIN', 
	(SELECT id FROM startups WHERE name LIKE 'test1_startup'),
	(SELECT id FROM users WHERE name LIKE 'test1_user')	
);