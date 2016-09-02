INSERT INTO question_group_category(question_id, group_category_id) VALUES

/*GENERAL INFO*/
((SELECT id FROM questions WHERE text LIKE 'What Market are you tackling?'),
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'What is the stage of your primary product?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'What type of product are you building?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'What is your monthly revenue (Approximately in Euros)?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'What is your monthly burn rate (Approximately in Euros)?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'Which of these options describes your current situation best?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'For how long have you been profitable?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'For how many months has this described your situation?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'How many months have you lived in Silicon Valley?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'Are all these results accurate or did you just want to check out the questionnaire?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),

((SELECT id FROM questions WHERE text LIKE 'Do you have any feedback on the questionnaire or for the Startup Project Europe?'),										
(SELECT id FROM group_categories WHERE name LIKE 'General Venture Info')),


/*GETTING STARTED*/
((SELECT id FROM questions WHERE text LIKE 'What is your motivation for this Startup?'),										
(SELECT id FROM group_categories WHERE name LIKE 'Getting Started')),

((SELECT id FROM questions WHERE text LIKE 'For how long have the Founders known each other before the Company was started?'),										
(SELECT id FROM group_categories WHERE name LIKE 'Getting Started')),


/*Market Segmentation/Select a beachhead market*/
((SELECT id FROM questions WHERE text LIKE 'We tested our product with multiple market segments'),										
(SELECT id FROM group_categories WHERE name LIKE 'Market Segmentation/Select a beachhead market')),

((SELECT id FROM questions WHERE text LIKE 'We interviewed more than 10 potential customers in person within 3 months of starting product development'),										
(SELECT id FROM group_categories WHERE name LIKE 'Market Segmentation/Select a beachhead market')),

((SELECT id FROM questions WHERE text LIKE 'We regularly talk to our customers in person or by phone (or by other media)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Market Segmentation/Select a beachhead market')),

((SELECT id FROM questions WHERE text LIKE 'We experimented with acquiring customers from more than 5 different channels'),										
(SELECT id FROM group_categories WHERE name LIKE 'Market Segmentation/Select a beachhead market')),

((SELECT id FROM questions WHERE text LIKE 'We chose to focus on a particular market segment, by focusing on a particular region, demographic or product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Market Segmentation/Select a beachhead market')),


/*Build an End User Profile*/
((SELECT id FROM questions WHERE text LIKE 'We have identified a particular type of user that is the most important to satisfy'),										
(SELECT id FROM group_categories WHERE name LIKE 'Build an End User Profile')),


/*Calculate the TAM for the Beachhead Market*/
((SELECT id FROM questions WHERE text LIKE 'We have used the demographics from our ""typical"" End User to determine quantitatively how large this market segment is for the whole of Europe (beachhead market)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the TAM for the Beachhead Market')),

((SELECT id FROM questions WHERE text LIKE 'The size of our beachhead market is over 10 Million in annual revenue'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the TAM for the Beachhead Market')),


/*Profile the Persona for the Beachhead Market*/
((SELECT id FROM questions WHERE text LIKE 'We tested persona''s and archetypes to describe our different types of users'),										
(SELECT id FROM group_categories WHERE name LIKE 'Profile the Persona for the Beachhead Market')),

((SELECT id FROM questions WHERE text LIKE 'We have identified a particular type of user that is the most important to satisfy'),										
(SELECT id FROM group_categories WHERE name LIKE 'Profile the Persona for the Beachhead Market')),

((SELECT id FROM questions WHERE text LIKE 'We have defined what must happen for the user in order for them to consider our product a ""must-have""'),										
(SELECT id FROM group_categories WHERE name LIKE 'Profile the Persona for the Beachhead Market')),


/*Identify your next 10 Customers*/
((SELECT id FROM questions WHERE text LIKE 'We have identified at least 10 potential customers who fit the ""typical"" End User Profile'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify your next 10 Customers')),

((SELECT id FROM questions WHERE text LIKE 'We have contacted these additional potential customers to validate their similarity to our Persona / ""typical"" End User Profile'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify your next 10 Customers')),

((SELECT id FROM questions WHERE text LIKE 'We regularly talk to our customers in person or by phone (or by other media)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify your next 10 Customers')),

((SELECT id FROM questions WHERE text LIKE 'We have qualified our Customers (e.g. ''Lighthouse'', ''Linchpin'', or other accepted qualifications)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify your next 10 Customers')),

((SELECT id FROM questions WHERE text LIKE 'We regularly survey our users'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify your next 10 Customers')),

((SELECT id FROM questions WHERE text LIKE 'Our customers would be very disappointed, if our product disappeared'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify your next 10 Customers')),

((SELECT id FROM questions WHERE text LIKE 'With the product at its current stage of development our customers would consider our product a ""must-have"" rather than a ""nice-to-have""'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify your next 10 Customers')),


/*Full Life Cycle Use Case*/
((SELECT id FROM questions WHERE text LIKE 'We know exactly what a day in the life of our user is like'),										
(SELECT id FROM group_categories WHERE name LIKE 'Full Life Cycle Use Case')),

((SELECT id FROM questions WHERE text LIKE 'We have created use case studies that describe how our ""typical end user"" finds out about our product, acquires it, uses it, gets value from it, pays for it and buys more and/or tells others about it'),										
(SELECT id FROM group_categories WHERE name LIKE 'Full Life Cycle Use Case')),


/*Hi Level Product Specification*/
((SELECT id FROM questions WHERE text LIKE 'We have created a visual representation of our product with the focus on the benefits for the Customer (created by the features) instead of just the features'),										
(SELECT id FROM group_categories WHERE name LIKE 'Hi Level Product Specification')),

((SELECT id FROM questions WHERE text LIKE 'We have created a brochure or leaflet with features, function and benefits to the Customer to clarify our product offering'),										
(SELECT id FROM group_categories WHERE name LIKE 'Hi Level Product Specification')),


/*Quantify the Value Proposition*/
((SELECT id FROM questions WHERE text LIKE 'We have mapped the benefits of our product to the top priority of our ""typical"" End User'),										
(SELECT id FROM group_categories WHERE name LIKE 'Quantify the Value Proposition')),

((SELECT id FROM questions WHERE text LIKE 'We know what ROI our product delivers and have data to back it up'),										
(SELECT id FROM group_categories WHERE name LIKE 'Quantify the Value Proposition')),


/*Define your Core (=source of competitive advantage*/
((SELECT id FROM questions WHERE text LIKE 'At least ONE of the Founders has technical domain experience and works on the product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Define your Core (=source of competitive advantage')),

((SELECT id FROM questions WHERE text LIKE 'At least ONE of the Founders has previous experience tackling the same market'),										
(SELECT id FROM group_categories WHERE name LIKE 'Define your Core (=source of competitive advantage')),

((SELECT id FROM questions WHERE text LIKE 'We have more than 3 people on our team devoted to Customer Service'),										
(SELECT id FROM group_categories WHERE name LIKE 'Define your Core (=source of competitive advantage')),


/*Chart your Competitive Position*/
((SELECT id FROM questions WHERE text LIKE 'We have determined how our product meets the ""typical"" End User''s top 2 priorities'),										
(SELECT id FROM group_categories WHERE name LIKE 'Chart your Competitive Position')),


/*Determine the Customer's DMU*/
((SELECT id FROM questions WHERE text LIKE 'We have determined who makes the ultimate purchase decision for our product and who are the advocates in favor of our product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Determine the Customer''s DMU')),

((SELECT id FROM questions WHERE text LIKE 'We have determined all the influential people who have sway over the purchasing decision'),										
(SELECT id FROM group_categories WHERE name LIKE 'Determine the Customer''s DMU')),

((SELECT id FROM questions WHERE text LIKE 'We have made a diagram depicting the entire Decision-Making Unit'),										
(SELECT id FROM group_categories WHERE name LIKE 'Determine the Customer''s DMU')),

((SELECT id FROM questions WHERE text LIKE 'We regularly talk to our customers in person or by phone (or by other media)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Determine the Customer''s DMU')),


/*Map the Process to acquire a paying Customer*/
((SELECT id FROM questions WHERE text LIKE 'We have mapped the process by which a Customer decides to purchase our product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Process to acquire a paying Customer')),

((SELECT id FROM questions WHERE text LIKE 'We have determined the sales cycle for our product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Process to acquire a paying Customer')),

((SELECT id FROM questions WHERE text LIKE 'We have identified budgetary, regulatory or compliance hurdles that would slow down our ability to make a sale'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Process to acquire a paying Customer')),


/*Map the Sales Processs to acquire a  Customer*/
((SELECT id FROM questions WHERE text LIKE 'We have developed short-term, medium-term and long term sales strategies for our product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'We offer free trials'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'We have put significant resources on improving our brand experience'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'We have spent significant resources to optimizing our first user experience'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'We use conversion funnels'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'We have devoted significant resources to optimizing our conversion funnel'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'We experimented with acquiring customers from more than 5 different channels'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'We regularly survey our users'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),

((SELECT id FROM questions WHERE text LIKE 'Our sales people have call or meeting scripts'),										
(SELECT id FROM group_categories WHERE name LIKE 'Map the Sales Processs to acquire a  Customer')),


/*Design a Business Model*/
((SELECT id FROM questions WHERE text LIKE 'We have examined the existing business models in our industry to determine how value is captured'),										
(SELECT id FROM group_categories WHERE name LIKE 'Design a Business Model')),

((SELECT id FROM questions WHERE text LIKE 'We have chosen the business model for our venture'),										
(SELECT id FROM group_categories WHERE name LIKE 'Design a Business Model')),


/*Set your Pricing Framework*/
((SELECT id FROM questions WHERE text LIKE 'We adjust pricing for every customer'),										
(SELECT id FROM group_categories WHERE name LIKE 'Set your Pricing Framework')),


/*Calculate the LTV*/
((SELECT id FROM questions WHERE text LIKE 'We have identified our Average Revenue Per User (ARPU) and/or Customer''s Lifetime Value (LTV)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the LTV')),

((SELECT id FROM questions WHERE text LIKE 'We have implemented ways to improve ARPU and/or LTV'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the LTV')),

((SELECT id FROM questions WHERE text LIKE 'Our Average Revenue Per User (ARPU) and/or Lifetime Value (LTV) have reached an equilibrium and the value does not fluctuate much'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the LTV')),


/*Calculate the COCA*/
((SELECT id FROM questions WHERE text LIKE 'We have determined the Customer Acquisition Cost (CAC)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the COCA')),

((SELECT id FROM questions WHERE text LIKE 'We have implemented ways to improve CAC'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the COCA')),

((SELECT id FROM questions WHERE text LIKE 'Our CAC has reached an equilibrium and the value does not float much'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate the COCA')),


/*Identify Key Assumptions*/
((SELECT id FROM questions WHERE text LIKE 'We identified the riskiest hypotheses about our business in order to test them first'),										
(SELECT id FROM group_categories WHERE name LIKE 'Identify Key Assumptions')),


/*Test Key Assumptions*/
((SELECT id FROM questions WHERE text LIKE 'We have tested the riskiest hypotheses about our business'),										
(SELECT id FROM group_categories WHERE name LIKE 'Test Key Assumptions')),

((SELECT id FROM questions WHERE text LIKE 'We regularly survey our users'),										
(SELECT id FROM group_categories WHERE name LIKE 'Test Key Assumptions')),


/*Define the MBVP*/
((SELECT id FROM questions WHERE text LIKE 'We built minimal viable products to test our hypotheses'),										
(SELECT id FROM group_categories WHERE name LIKE 'Define the MBVP')),

((SELECT id FROM questions WHERE text LIKE 'Our product has minimum viable critical mass'),										
(SELECT id FROM group_categories WHERE name LIKE 'Define the MBVP')),

/*Show that dogs will eat the dog food*/
((SELECT id FROM questions WHERE text LIKE 'We measure activation and retention'),										
(SELECT id FROM group_categories WHERE name LIKE 'Show that dogs will eat the dog food')),

((SELECT id FROM questions WHERE text LIKE 'We use conversion funnels'),										
(SELECT id FROM group_categories WHERE name LIKE 'Show that dogs will eat the dog food')),

((SELECT id FROM questions WHERE text LIKE 'We have devoted significant resources to optimizing our conversion funnel'),										
(SELECT id FROM group_categories WHERE name LIKE 'Show that dogs will eat the dog food')),

((SELECT id FROM questions WHERE text LIKE 'We have at least 3 users who we know that love our product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Show that dogs will eat the dog food')),

((SELECT id FROM questions WHERE text LIKE 'We have a market segment where we have closed 10+ deals with at least one reference customer that would be recognizable to our potential customers'),										
(SELECT id FROM group_categories WHERE name LIKE 'Show that dogs will eat the dog food')),

((SELECT id FROM questions WHERE text LIKE 'We have a market segment where we have closed 25+ deals with at least one reference customer that would be recognizable to our potential customers'),										
(SELECT id FROM group_categories WHERE name LIKE 'Show that dogs will eat the dog food')),


/*Calculate TAM for follow-on Markets*/
/*((SELECT id FROM questions WHERE text LIKE 'We have identfied "follow-on" markets to expand into after dominating our beachhead market'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate TAM for follow-on Markets')),*/

/*
((SELECT id FROM questions WHERE text LIKE 'We have calculated the size of these follow-on markets (for the whole of Europe) using primary sources'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate TAM for follow-on Markets')),*/

((SELECT id FROM questions WHERE text LIKE 'The size of our beachhead market is over 10 Million in annual revenue each'),										
(SELECT id FROM group_categories WHERE name LIKE 'Calculate TAM for follow-on Markets')),


/*Develop a Product Plan*/
((SELECT id FROM questions WHERE text LIKE 'We have specified product features beyond what we built for the beachhead market'),										
(SELECT id FROM group_categories WHERE name LIKE 'Develop a Product Plan')),

((SELECT id FROM questions WHERE text LIKE 'Our customers would be very disappointed, if our product disappeared'),										
(SELECT id FROM group_categories WHERE name LIKE 'Develop a Product Plan')),

((SELECT id FROM questions WHERE text LIKE 'We have at least 3 users who we know that love our product'),										
(SELECT id FROM group_categories WHERE name LIKE 'Develop a Product Plan')),

((SELECT id FROM questions WHERE text LIKE 'With the product at its current stage of development our customers would consider our product a ""must-have"" rather than a ""nice-to-have""'),										
(SELECT id FROM group_categories WHERE name LIKE 'Develop a Product Plan')),

((SELECT id FROM questions WHERE text LIKE 'We have significantly changed our product based on feedback from strategic reference accounts'),										
(SELECT id FROM group_categories WHERE name LIKE 'Develop a Product Plan')),

((SELECT id FROM questions WHERE text LIKE 'We have significantly modified our value proposition based on feedback from our paying customers'),										
(SELECT id FROM group_categories WHERE name LIKE 'Develop a Product Plan')),


/*General*/
((SELECT id FROM questions WHERE text LIKE 'Our product has minimum viable critical mass'),										
(SELECT id FROM group_categories WHERE name LIKE 'General')),


/*Internally*/
((SELECT id FROM questions WHERE text LIKE 'We have more than 3 people on our team devoted to Customer Service'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'We are hiring aggressively'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'We have hired a product manager'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'We have hired an engineering manager'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'Our reporting structure has more than 2 layers'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'We have a fully employed a finance department (CFO, controller, admin)'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'We have an HR department'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'We do Business Process Outsourcing'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),

((SELECT id FROM questions WHERE text LIKE 'Our sales people are evaluated on well defined metrics'),										
(SELECT id FROM group_categories WHERE name LIKE 'Internally')),


/*Management*/
((SELECT id FROM questions WHERE text LIKE 'Overall cost is predicted accurately'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'Clear objectives are formulated, documented and implemented'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'Functional, technical and performance requirements are formulated and documented'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'An experienced manager is available to lead the company'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'Qualified, experienced and motivated team members are part of the startup'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'Plans and estimates are reliable'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'There is an appropriate transparency about status and progress'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'The plan is adjusted on a robust fact base'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'Continuity of the startup (e.g. key members getting ill, etc.) is assured'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'Roles and responsibilities are clear, in place and accepted'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management')),

((SELECT id FROM questions WHERE text LIKE 'Risk Tools are in place and mitigation activities are implemented'),										
(SELECT id FROM group_categories WHERE name LIKE 'Management'));
