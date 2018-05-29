create function hapuspesanan()
  returns trigger
language plpgsql
as $$
BEGIN
  Delete from pesanan_customer where id_pesanan = old.id;
 Delete from pesanan_room where id_pesanan = old.id;
 return new;
 end;
$$;

