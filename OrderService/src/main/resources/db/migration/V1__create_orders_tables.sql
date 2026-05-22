create table orders(id bigserial primary key,
                    cust_id varchar(255) not null,
                    total_amount numeric(10,2) not null,
                    status varchar(50),
                    created_at timestamp not null,
                    updated_at timestamp);