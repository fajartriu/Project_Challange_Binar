SELECT *
FROM information_schema.routines
WHERE routine_type = 'PROCEDURE';

create or replace procedure delete_user(in userId UUID)
language plpgsql
as $$
begin
	delete from users u where u.id = userId;
end; $$

create or replace procedure create_users(in userId UUID, in username text, in emailAddress text, in "password" text)
language plpgsql
as $$
begin
	INSERT INTO users (id ,username, email_address , "password")
	VALUES (userId, username, emailAddress, "password");
end; $$


create or replace procedure update_user(in userId UUID, in usernameUpdate text, in emailAddressUpdate text, in passwordUpdate text)
language plpgsql
as $$
begin
	UPDATE users 
		SET username  = usernameUpdate,
	    email_address  = emailAddressUpdate,
	    "password" = passwordUpdate
	WHERE id = userId;
end; $$

drop procedure tes3 

call update_user('a0b8c938-0f64-4113-aa61-66fd4321f852', 'man', 't', 'a')

