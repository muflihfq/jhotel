create or replace function hapusroom()
  returns trigger
language plpgsql
as $$
|
BEGIN
  DELETE from room where nomor_kamar = old.nomor_kamar;
  delete from room_hotel where nomor_kamar = old.nomor_kamar;
  delete from pesanan where id = old.id_pesanan;
 return new;
 end;
$$;

