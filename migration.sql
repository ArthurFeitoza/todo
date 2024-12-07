-- public.tasks definição

CREATE TABLE public.tasks (
	id serial primary KEY,
	description VARCHAR NOT NULL,
	status VARCHAR default 'A' NOT NULL,
	created_at TIMESTAMP default CURRENT_TIMESTAMP not NULL,
	started_at TIMESTAMP,
	completed_at TIMESTAMP
);
