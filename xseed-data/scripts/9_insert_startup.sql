INSERT INTO startups(name, country_id)
VALUES('test1_startup', (SELECT id FROM countries WHERE name LIKE 'Bulgaria'));