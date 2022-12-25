INSERT INTO public."label" (id,name) VALUES
	 ('9d9c28b2-add1-47eb-bef1-da64ad0f080e','LL1'),
	 ('7f6dc2d1-904d-48a1-a021-3133999fd648','LL2');

INSERT INTO public.technology (id,born_year,home_url,note,rating,technology_name,wiki_url) VALUES
	 ('12ba6b59-ea2a-4219-bab8-5a9a365f6953',2000,'www.ne.com','note2',3,'Java','wiki2'),
	 ('6ff06137-b33d-4a94-9979-5fc62491145b',1985,'www.home.com','note1',5,'Phyton','wiki 1');
	 
INSERT INTO public.technology_label (id,user_name,label_id,technology_id) VALUES
	 ('8d6da16d-193e-4f77-874b-b5042555912f','user1','9d9c28b2-add1-47eb-bef1-da64ad0f080e','12ba6b59-ea2a-4219-bab8-5a9a365f6953'),
	 ('467fe120-b25d-4550-9b60-ad807671fa4c','user 5','9d9c28b2-add1-47eb-bef1-da64ad0f080e','6ff06137-b33d-4a94-9979-5fc62491145b');
