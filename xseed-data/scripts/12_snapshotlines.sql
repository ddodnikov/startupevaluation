INSERT INTO snapshotlines(multiple_answers_id, text_response, question_id, selected_answer_id, snapshot_id) VALUES
/*1*/
(
	(array[
		(SELECT id FROM answer_options WHERE text LIKE 'Games' AND identifier LIKE 'MTG12'),
		(SELECT id FROM answer_options WHERE text LIKE 'Internet of Things' AND identifier LIKE 'MTG14'),
		(SELECT id FROM answer_options WHERE text LIKE 'Personal Health & Welfare' AND identifier LIKE 'MTG15'),
		(SELECT id FROM answer_options WHERE text LIKE 'Mobile' AND identifier LIKE 'MTG17'),
		(SELECT id FROM answer_options WHERE text LIKE 'Other' AND identifier LIKE 'MTG25')
		]::int[]),
(null),
(SELECT id FROM questions WHERE text LIKE 'What Market are you tackling?'),
(null),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),
/*2*/
(
	(array[
		(SELECT id FROM answer_options WHERE text LIKE 'In Development' AND identifier LIKE 'SPG2'),
		(SELECT id FROM answer_options WHERE text LIKE 'Working Prototype' AND identifier LIKE 'SPG3')
	]::int[]),
(null),
(SELECT id FROM questions WHERE text LIKE 'What is the stage of your primary product'),
(null),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),
/*3*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'What type of product are you building'),
(SELECT id FROM answer_options WHERE text LIKE 'Other' AND identifier LIKE 'PTG14'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),
/*4*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'What is your monthly revenue (Approximately in Euros)?'),
(SELECT id FROM answer_options WHERE text LIKE '0' AND identifier LIKE 'MRG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),
/*5*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'What is your monthly burn rate (Approximately in Euros)?'),
(SELECT id FROM answer_options WHERE text LIKE '0' AND identifier LIKE 'MRG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),
/*6*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'For how long have you been profitable?'),
(SELECT id FROM answer_options WHERE text LIKE 'Not profitable yet - expected in less than 6 months' AND identifier LIKE 'PPG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),
/*7*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Which of these options describes your current situation best?'),
(SELECT id FROM answer_options WHERE text LIKE 'Validating that our customers actually want our product' AND identifier LIKE 'CSG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),
/*8*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'For how many months has this described your situation?'),
(SELECT id FROM answer_options WHERE text LIKE '3 -  6 months' AND identifier LIKE 'DPSG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*9*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'What is your motivation for this Startup?'),
(SELECT id FROM answer_options WHERE text LIKE 'You have a passion' AND identifier LIKE 'MG7'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*10*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'For how long have the Founders known each other before the Company was started?'),
(SELECT id FROM answer_options WHERE text LIKE '3 - 6 months' AND identifier LIKE 'FPG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*11*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We identified the riskiest hypotheses about our business in order to test them first'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*12*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We tested our product with multiple market segments'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*13*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We tested persona''s and archetypes to describe our different types of users'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*14*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We built minimal viable products to test our hypotheses'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*15*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We interviewed more than 10 potential customers in person within 3 months of starting product development'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*16*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We know exactly what a day in the life of our user is like'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*17*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We regularly talk to our customers in person or by phone (or by other media)'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*18*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have identified a particular type of user that is the most important to satisfy'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*19*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We measure activation and retention'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*20*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We use conversion funnels'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*21*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We offer free trials'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*22*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have put significant resources on improving our brand experience'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*23*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have defined what must happen for the user in order for them to consider our product a ""must-have""'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*24*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have spent significant resources to optimizing our first user experience'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*25*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have devoted significant resources to optimizing our conversion funnel'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*26*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We experimented with acquiring customers from more than 5 different channels'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*27*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have identified our Average Revenue Per User (ARPU) and/or Customer''s Lifetime Value (LTV)'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*28*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Our Average Revenue Per User (ARPU) and/or Lifetime Value (LTV) have reached an equilibrium and the value does not fluctuate much'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*29*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We regularly survey our users'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*30*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have more than 3 people on our team devoted to Customer Service'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*31*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have created use case studies that describe how our ""typical end user"" finds out about our product, acquires it, uses it, gets value from it, pays for it and buys more and/or tells others about it'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*32*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We are hiring aggressively'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*33*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have hired a product manager'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*34*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have hired an engineering manager'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*35*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Our reporting structure has more than 2 layers'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*36*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have a fully employed a finance department (CFO, controller, admin)'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*37*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have an HR department'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*38*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We do Business Process Outsourcing'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*39*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Our product has minimum viable critical mass'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*40*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We chose to focus on a particular market segment, by focusing on a particular region, demographic or product'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*41*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'The size of our beachhead market is over 10 Million in annual revenue each'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*42*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have specified product features beyond what we built for the beachhead market'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),



/*43*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Our customers would be very disappointed, if our product disappeared'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*44*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have at least 3 users who we know that love our product'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*45*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'With the product at its current stage of development our customers would consider our product a ""must-have"" rather than a ""nice-to-have""'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*46*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have a market segment where we have closed 10+ deals with at least one reference customer that would be recognizable to our potential customers'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*47*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have a market segment where we have closed 25+ deals with at least one reference customer that would be recognizable to our potential customers'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*48*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have significantly changed our product based on feedback from strategic reference accounts'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*49*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have significantly modified our value proposition based on feedback from our paying customers'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*50*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Our sales people have call or meeting scripts'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*51*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Our sales people are evaluated on well defined metrics'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*52*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We know what ROI our product delivers and have data to back it up'),
(SELECT id FROM answer_options WHERE text LIKE 'TRUE' AND identifier LIKE 'YNG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*53*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We adjust pricing for every customer'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*54*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have used the demographics from our ""typical"" End User to determine quantitatively how large this market segment is for the whole of Europe (beachhead market)'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*55*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'The size of our beachhead market is over 10 Million in annual revenue'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*56*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have identified at least 10 potential customers who fit the ""typical"" End User Profile'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*57*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have contacted these additional potential customers to validate their similarity to our Persona / ""typical"" End User Profile'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*58*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have qualified our Customers (e.g. ''Lighthouse'', ''Linchpin'', or other accepted qualifications)'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*59*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have created a visual representation of our product with the focus on the benefits for the Customer (created by the features) instead of just the features'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*60*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have created a brochure or leaflet with features, function and benefits to the Customer to clarify our product offering'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*61*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have mapped the benefits of our product to the top priority of our ""typical"" End User'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*62*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'At least ONE of the Founders has technical domain experience and works on the product'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*63*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'At least ONE of the Founders has previous experience tackling the same market'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*64*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have determined how our product meets the ""typical"" End User''s top 2 priorities'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*65*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have determined who makes the ultimate purchase decision for our product and who are the advocates in favor of our product'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*66*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have determined all the influential people who have sway over the purchasing decision'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*67*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have made a diagram depicting the entire Decision-Making Unit'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*68*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have mapped the process by which a Customer decides to purchase our product'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*69*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have determined the sales cycle for our product'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*70*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have identified budgetary, regulatory or compliance hurdles that would slow down our ability to make a sale'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*71*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have developed short-term, medium-term and long term sales strategies for our product'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*72*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have examined the existing business models in our industry to determine how value is captured'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*73*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have chosen the business model for our venture'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),


/*74*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have implemented ways to improve ARPU and/or LTV'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*75*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have determined the Customer Acquisition Cost (CAC)'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*76*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have implemented ways to improve CAC'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*77*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Our CAC has reached an equilibrium and the value does not float much'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*78*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'We have tested the riskiest hypotheses about our business'),
(SELECT id FROM answer_options WHERE text LIKE 'FALSE' AND identifier LIKE 'YNG2'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),


/*79*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Overall cost is predicted accurately'),
(SELECT id FROM answer_options WHERE text LIKE 'Neutral' AND identifier LIKE '6OG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*80*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Clear objectives are formulated, documented and implemented'),
(SELECT id FROM answer_options WHERE text LIKE 'Neutral' AND identifier LIKE '6OG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*81*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Functional, technical and performance requirements are formulated and documented'),
(SELECT id FROM answer_options WHERE text LIKE 'Neutral' AND identifier LIKE '6OG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*82*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'An experienced manager is available to lead the company'),
(SELECT id FROM answer_options WHERE text LIKE 'I do not know' AND identifier LIKE '6OG6'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*83*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Qualified, experienced and motivated team members are part of the startup'),
(SELECT id FROM answer_options WHERE text LIKE 'Neutral' AND identifier LIKE '6OG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*84*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Plans and estimates are reliable'),
(SELECT id FROM answer_options WHERE text LIKE 'Neutral' AND identifier LIKE '6OG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*85*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'There is an appropriate transparency about status and progress'),
(SELECT id FROM answer_options WHERE text LIKE 'Agree' AND identifier LIKE '6OG4'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*86*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'The plan is adjusted on a robust fact base'),
(SELECT id FROM answer_options WHERE text LIKE 'I do not know' AND identifier LIKE '6OG6'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*87*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Continuity of the startup (e.g. key members getting ill, etc.) is assured'),
(SELECT id FROM answer_options WHERE text LIKE 'I do not know' AND identifier LIKE '6OG6'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*88*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Roles and responsibilities are clear, in place and accepted'),
(SELECT id FROM answer_options WHERE text LIKE 'Neutral' AND identifier LIKE '6OG3'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*89*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Risk Tools are in place and mitigation activities are implemented'),
(SELECT id FROM answer_options WHERE text LIKE 'I do not know' AND identifier LIKE '6OG6'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*90*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'How many months have you lived in Silicon Valley?'),
(SELECT id FROM answer_options WHERE text LIKE 'I have never lived in Silicon Valley' AND identifier LIKE 'SVPG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*91*/
((null),
(null),
(SELECT id FROM questions WHERE text LIKE 'Are all these results accurate or did you just want to check out the questionnaire?'),
(SELECT id FROM answer_options WHERE text LIKE 'Yes, I certify that these results are accurate' AND identifier LIKE 'ARG1'),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01')),

/*92*/
((null),
('Not now'),
(SELECT id FROM questions WHERE text LIKE 'Do you have any feedback on the questionnaire or for the Startup Project Europe?'),
(null),
(SELECT id FROM snapshots WHERE name LIKE 'July_2016.07.01'));