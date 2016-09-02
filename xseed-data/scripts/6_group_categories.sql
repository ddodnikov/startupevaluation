INSERT INTO group_categories(name, phase_category_id) VALUES
('General Venture Info', 
	(SELECT id FROM phase_categories WHERE name LIKE 'What is your Venture about?')),

('Getting Started', 
	(SELECT id FROM phase_categories WHERE name LIKE 'Who is your Team')),

('Market Segmentation/Select a beachhead market',
	(SELECT id FROM phase_categories WHERE name LIKE 'Who is your Customer?')),
('Build an End User Profile', 
	(SELECT id FROM phase_categories WHERE name LIKE 'Who is your Customer?')),
('Calculate the TAM for the Beachhead Market', 
	(SELECT id FROM phase_categories WHERE name LIKE 'Who is your Customer?')),
('Profile the Persona for the Beachhead Market',
	(SELECT id FROM phase_categories WHERE name LIKE 'Who is your Customer?')),
('Identify your next 10 Customers', 
	(SELECT id FROM phase_categories WHERE name LIKE 'Who is your Customer?')),

('Full Life Cycle Use Case', 
	(SELECT id FROM phase_categories WHERE name LIKE 'What can you do for your Customer')),
('Hi Level Product Specification', 
	(SELECT id FROM phase_categories WHERE name LIKE 'What can you do for your Customer')),
('Quantify the Value Proposition', 
	(SELECT id FROM phase_categories WHERE name LIKE 'What can you do for your Customer')),
('Define your Core (=source of competitive advantage', 
	(SELECT id FROM phase_categories WHERE name LIKE 'What can you do for your Customer')),
('Chart your Competitive Position', 
	(SELECT id FROM phase_categories WHERE name LIKE 'What can you do for your Customer')),

('Determine the Customer''s DMU', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How does your Customer acquire your Product')),
('Map the Process to acquire a paying Customer', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How does your Customer acquire your Product')),
('Map the Sales Processs to acquire a  Customer', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How does your Customer acquire your Product')),

('Design a Business Model', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you make money off your Product')),
('Set your Pricing Framework', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you make money off your Product')),
('Calculate the LTV', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you make money off your Product')),
('Calculate the COCA', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you make money off your Product')),

('Identify Key Assumptions', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you design and build your Product')),
('Test Key Assumptions', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you design and build your Product')),
('Define the MBVP', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you design and build your Product')),
('Show that dogs will eat the dog food', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you design and build your Product')),

('Calculate TAM for follow-on Markets', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How to Scale your Business')),
('Develop a Product Plan',
	(SELECT id FROM phase_categories WHERE name LIKE 'How to Scale your Business')),

('General', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you scale your venture?')),
('Internally', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you scale your venture?')),

('Management', 
	(SELECT id FROM phase_categories WHERE name LIKE 'How do you run your venture?'));


