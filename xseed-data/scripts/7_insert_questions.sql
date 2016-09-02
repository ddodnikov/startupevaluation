INSERT INTO questions (algo, filter, kpi, text, answer_group_id, question_category_id, hasOther) VALUES
(false, true, false, 'What Market are you tackling?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'MTG'),
(SELECT id FROM question_categories WHERE type LIKE 'MULTIPLE_CHOICE'),
true), 

(false, true, false, 'What is the stage of your primary product?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'SPG'),
(SELECT id FROM question_categories WHERE type LIKE 'MULTIPLE_CHOICE'),
false), 

(false, true, false, 'What type of product are you building?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'PTG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
true),

(true, false, true, 'What is your monthly revenue (Approximately in Euros)?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'MRG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, true, 'What is your monthly burn rate (Approximately in Euros)?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'MRG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false),

(false, true, false, 'For how long have you been profitable?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'PPG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false),

(false, true, false, 'Which of these options describes your current situation best?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'CSG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false),

(true, false, true, 'For how many months has this described your situation?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'DPSG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false),

(true, false, false, 'What is your motivation for this Startup?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'MG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'For how long have the Founders known each other before the Company was started?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'FPG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false),

/*10*/
(true, false, false, 'We identified the riskiest hypotheses about our business in order to test them first',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We tested our product with multiple market segments',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We tested persona''s and archetypes to describe our different types of users',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We built minimal viable products to test our hypotheses',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We interviewed more than 10 potential customers in person within 3 months of starting product development',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We know exactly what a day in the life of our user is like',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We regularly talk to our customers in person or by phone (or by other media)',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have identified a particular type of user that is the most important to satisfy',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We measure activation and retention', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We use conversion funnels', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We offer free trials',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have put significant resources on improving our brand experience', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have defined what must happen for the user in order for them to consider our product a ""must-have""', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have spent significant resources to optimizing our first user experience', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have devoted significant resources to optimizing our conversion funnel', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We experimented with acquiring customers from more than 5 different channels',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have identified our Average Revenue Per User (ARPU) and/or Customer''s Lifetime Value (LTV)',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'Our Average Revenue Per User (ARPU) and/or Lifetime Value (LTV) have reached an equilibrium and the value does not fluctuate much',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We regularly survey our users',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false),  

(true, false, false, 'We have more than 3 people on our team devoted to Customer Service',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have created use case studies that describe how our ""typical end user"" finds out about our product, acquires it, uses it, gets value from it, pays for it and buys more and/or tells others about it',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We are hiring aggressively', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have hired a product manager', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have hired an engineering manager', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'Our reporting structure has more than 2 layers', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have a fully employed a finance department (CFO, controller, admin)', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

/*11Z*/
(true, false, false, 'We have an HR department',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 
(true, false, false, 'We do Business Process Outsourcing',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 
(true, false, false, 'Our product has minimum viable critical mass',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We chose to focus on a particular market segment, by focusing on a particular region, demographic or product', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'The size of our beachhead market is over 10 Million in annual revenue each',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have specified product features beyond what we built for the beachhead market',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

/*11AF*/
(true, false, false, 'Our customers would be very disappointed, if our product disappeared',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have at least 3 users who we know that love our product',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'With the product at its current stage of development our customers would consider our product a ""must-have"" rather than a ""nice-to-have""',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 
(true, false, false, 'We have a market segment where we have closed 10+ deals with at least one reference customer that would be recognizable to our potential customers',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have a market segment where we have closed 25+ deals with at least one reference customer that would be recognizable to our potential customers',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have significantly changed our product based on feedback from strategic reference accounts',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 
(true, false, false, 'We have significantly modified our value proposition based on feedback from our paying customers',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'Our sales people have call or meeting scripts',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'Our sales people are evaluated on well defined metrics',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We know what ROI our product delivers and have data to back it up',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We adjust pricing for every customer',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have used the demographics from our ""typical"" End User to determine quantitatively how large this market segment is for the whole of Europe (beachhead market)',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'The size of our beachhead market is over 10 Million in annual revenue',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have identified at least 10 potential customers who fit the ""typical"" End User Profile',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 
(true, false, false, 'We have contacted these additional potential customers to validate their similarity to our Persona / ""typical"" End User Profile',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have qualified our Customers (e.g. ''Lighthouse'', ''Linchpin'', or other accepted qualifications)',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have created a visual representation of our product with the focus on the benefits for the Customer (created by the features) instead of just the features',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have created a brochure or leaflet with features, function and benefits to the Customer to clarify our product offering',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have mapped the benefits of our product to the top priority of our ""typical"" End User',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'At least ONE of the Founders has technical domain experience and works on the product',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'At least ONE of the Founders has previous experience tackling the same market',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have determined how our product meets the ""typical"" End User''s top 2 priorities',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have determined who makes the ultimate purchase decision for our product and who are the advocates in favor of our product',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have determined all the influential people who have sway over the purchasing decision',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have made a diagram depicting the entire Decision-Making Unit', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have mapped the process by which a Customer decides to purchase our product',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false),

(true, false, false, 'We have determined the sales cycle for our product',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have identified budgetary, regulatory or compliance hurdles that would slow down our ability to make a sale',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false),

(true, false, false, 'We have developed short-term, medium-term and long term sales strategies for our product',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have examined the existing business models in our industry to determine how value is captured',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have chosen the business model for our venture',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have implemented ways to improve ARPU and/or LTV',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have determined the Customer Acquisition Cost (CAC)',
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'We have implemented ways to improve CAC', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

(true, false, false, 'Our CAC has reached an equilibrium and the value does not float much', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 
(true, false, false, 'We have tested the riskiest hypotheses about our business', 
(SELECT id FROM answer_groups WHERE identifier LIKE 'YNG'),
(SELECT id FROM question_categories WHERE type LIKE 'YES_NO'),
false), 

/*12AJ*/
(true, false, false, 'Overall cost is predicted accurately',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Clear objectives are formulated, documented and implemented',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Functional, technical and performance requirements are formulated and documented',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'An experienced manager is available to lead the company',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Qualified, experienced and motivated team members are part of the startup', 
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Plans and estimates are reliable',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'There is an appropriate transparency about status and progress',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'The plan is adjusted on a robust fact base',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Continuity of the startup (e.g. key members getting ill, etc.) is assured',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Roles and responsibilities are clear, in place and accepted',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Risk Tools are in place and mitigation activities are implemented',
(SELECT id FROM answer_groups WHERE identifier LIKE '6OG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'How many months have you lived in Silicon Valley?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'SVPG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Are all these results accurate or did you just want to check out the questionnaire?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'ARG'),
(SELECT id FROM question_categories WHERE type LIKE 'SINGLE_CHOICE'),
false), 

(true, false, false, 'Do you have any feedback on the questionnaire or for the Startup Project Europe?',
(SELECT id FROM answer_groups WHERE identifier LIKE 'TG'),
(SELECT id FROM question_categories WHERE type LIKE 'TEXT'),
false);


