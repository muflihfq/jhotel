create function hapushotel()
  returns trigger
language plpgsql
as $$
BEGIN
  delete from room where nomor_kamar = old.nomor_kamar;
  delete from room_hotel where id_hotel = old.id_hotel
  delete from pesanan_room where id_pesanan = old.id_pesanan;
  delete from pesanan where id = old.id_pesanan;
  delete from pesanan_customer where id_pesanan = old.id_pesanan;
 return new;
 end;
$$;

