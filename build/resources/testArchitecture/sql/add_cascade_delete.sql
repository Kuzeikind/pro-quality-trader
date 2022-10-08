alter table trader.user_has_stock
drop constraint fk_user_id,
add constraint fk_user_id
   foreign key (user_id)
   references trader.users(id)
   on delete cascade;

alter table trader.user_has_stock
drop constraint fk_stock_id,
add constraint fk_stock_id
   foreign key (stock_id)
   references trader.stocks(id)
   on delete cascade;
