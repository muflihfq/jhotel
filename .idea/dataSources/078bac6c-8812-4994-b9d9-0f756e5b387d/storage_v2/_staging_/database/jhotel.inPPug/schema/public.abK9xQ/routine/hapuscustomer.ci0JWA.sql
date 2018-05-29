create function hapuscustomer()
  returns trigger
language plpgsql
as $$
BEGIN
  insert into hapus_customer values (old.id_customer, old.)
  delete from customer where id = old.id_customer;
  delete from pesanan where id = old.id_pesanan;

 return new;
 end;
$$;

