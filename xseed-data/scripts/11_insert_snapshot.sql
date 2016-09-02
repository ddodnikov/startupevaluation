INSERT INTO snapshots (date, name, startup_id, survey_id, user_id) VALUES
(now(), 'July_2016.07.01',
(SELECT id FROM startups WHERE name LIKE 'test1_startup'),
(SELECT id FROM surveys WHERE name LIKE 'Survey N1'),
(SELECT id FROM users WHERE name LIKE 'test1_user'));