-- CREATE DATABASE
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- CREATE TABLES

CREATE TABLE public.users (
	user_id varchar(255) NOT NULL,
	user_name varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
);


CREATE TABLE public.invitations (
	invitation_id varchar(255) NOT NULL,
	status varchar(255) NULL,
	user_id varchar(255) NULL,
	CONSTRAINT invitations_pkey PRIMARY KEY (invitation_id),
	CONSTRAINT fk4wvuj0rfixe8dttrqk1o4qdh FOREIGN KEY (user_id) REFERENCES public.users(user_id)
);

CREATE TABLE public.shopping_lists (
	list_id varchar(255) NOT NULL,
	created_when timestamp NULL,
	list_name varchar(255) NULL,
	updated_when timestamp NULL,
	CONSTRAINT shopping_lists_pkey PRIMARY KEY (list_id)
);

CREATE TABLE public.list_items (
	item_id varchar(255) NOT NULL,
	item_name varchar(255) NULL,
	marked_as_checked bool NULL,
	quantity_desc varchar(255) NULL,
	list_id varchar(255) NULL,
	CONSTRAINT list_items_pkey PRIMARY KEY (item_id),
	CONSTRAINT fklhf0d2q45jjh11s3djd55i6ec FOREIGN KEY (list_id) REFERENCES public.shopping_lists(list_id)
);

CREATE TABLE public.users_lists (
	list_id varchar(255) NOT NULL,
	user_id varchar(255) NOT NULL,
	CONSTRAINT fkjgo95u0dtudqcry0l04rkjmfp FOREIGN KEY (user_id) REFERENCES public.users(user_id),
	CONSTRAINT fkoet5up9ff99a2cd1hx10a5s45 FOREIGN KEY (list_id) REFERENCES public.shopping_lists(list_id)
);

-- INSERT DATA
-- USERS TABLE
INSERT INTO public.users (user_id,user_name) VALUES (
'abc100','Mark');
INSERT INTO public.users (user_id,user_name) VALUES (
'2a','John');

-- SHOPPING_LISTS TABLE
INSERT INTO public.shopping_lists (list_id,created_when,list_name,updated_when) VALUES (
'bd18c66b-e839-45cb-b059-ceb8a9742601','2017-05-10 12:19:21.173','forDinner','2017-05-10 12:19:23.185');

-- LIST_ITEMS TABLE
INSERT INTO public.list_items (item_id,item_name,marked_as_checked,quantity_desc,list_id) VALUES (
'db048df9-00e2-43a1-8ecb-06d0205843b6','ham',false,'1 kg','bd18c66b-e839-45cb-b059-ceb8a9742601');
INSERT INTO public.list_items (item_id,item_name,marked_as_checked,quantity_desc,list_id) VALUES (
'b658e6aa-9cbc-414b-9392-8d2690525a9c','eggs',false,'10','bd18c66b-e839-45cb-b059-ceb8a9742601');
INSERT INTO public.list_items (item_id,item_name,marked_as_checked,quantity_desc,list_id) VALUES (
'a70dc368-2287-4444-b9ec-4aeb10f6b641','water',false,'2 bottles','bd18c66b-e839-45cb-b059-ceb8a9742601');
INSERT INTO public.list_items (item_id,item_name,marked_as_checked,quantity_desc,list_id) VALUES (
'2d761d0f-0767-471d-a5d1-ba1b478dbf09','bread',false,'1','bd18c66b-e839-45cb-b059-ceb8a9742601');

-- USERS_LISTS TABLE
INSERT INTO public.users_lists (list_id,user_id) VALUES (
'bd18c66b-e839-45cb-b059-ceb8a9742601','abc100');
INSERT INTO public.users_lists (list_id,user_id) VALUES (
'bd18c66b-e839-45cb-b059-ceb8a9742601','2a');


