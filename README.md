# <p align="center">PPAPB - Travelakuy</p>
<p align="center">
  <a href="https://www.figma.com/file/jAsZnxNifcVJkwUvbqDHYG/Travelakuy---UAS-PPAPB?type=design&node-id=0%3A1&mode=design&t=84lSVEXHT3O5tQsA-1">
    USER FLOW - TRAVELAKUY
  </a>
</p>
 
## Admin:
- **Deskripsi:**
  - Saat admin masuk, halaman adminmenu ditampilkan.
  - Terdapat recyclerview dengan formulir Create travel di bawahnya.
  - Setiap item pada recyclerview admin memiliki tombol cancel/close untuk melakukan Delete pada item travel.
  - Ketika item recyclerview ditekan, data asal, tujuan, dan harga ditampilkan di form untuk melakukan Update.

## User Belum Masuk:
- **Deskripsi:**
  - Tampilan awal menampilkan tablayout Signin.
  - Tombol sign in akan menjadi aktif ketika semua data telah diisi; jika belum, tombol tersebut akan berada dalam keadaan disable.
  - Setelah berhasil login, pengguna akan dialihkan ke tablayout login yang membawa ke halaman Home.

## User Sudah Masuk:
- **Deskripsi:**
  - Tampilan Home dengan recyclerview menampilkan item travel dari admin.
  - Recyclerview mirip dengan milik admin, namun tidak ada tombol close.
  - Ketika item travel diklik, akan membuka halaman Order Detail yang menampilkan asal dan tujuan sesuai dengan item travel yang dipilih.
  - Pengguna hanya perlu mengisi tanggal dan kelas kereta (spinner) ditampilkan juga harga awal sesuai yang tertera di item travel, setelah terisi semua total harga akan muncul dan tombol pesan sekarang dapat ditekan.
  - Setelah melakukan pemesanan, pengguna dialihkan ke halaman History.
  - Tombol bottom navigation Profile akan mengarahkan ke halaman Profile.

## Tambahan:
- **Online / Offline Status:**
  - Ketika aplikasi tidak terhubung dengan internet, aplikasi akan menampilkan teks di bawah bottom navigation "@string/offline_status" dengan background warna "@color/danger" yang akan selalau ditampilkan **selama aplikasi tidak terhubung dengan internet**
  - Saat kondisi offline maka semua data baru inputan admin akan disimpan sementara di Room
  - Ketika berhasil terhubung dengan internet, aplikasi akan menampilkan teks "@string/online_status" dan background warna "@color/success" namun setelah satu detik, text view yang menampilkan online/offline status visibilitynya diubah menjadi gone
  - Saat kembali online, semua data baru admin yang ada di Room, diunggah ke Firestore database.

- **Notifikasi:**
  - Setelah user berhasil memesan tiket, tampilkan notifikasi bahwa pemesanan berhasil.
 
- **SharedPreference:**
  - Setelah pengguna berhasil mendaftar dan/atau masuk ke aplikasi
  - User dapat keluar masuk aplikasi tanpa perlu mengisi formulir login lagi setiap kali masuk aplikasi.
  - Tombol kembali pada navigasi ponsel jika ditekan saat berada di halaman Home/Beranda seharusnya langsung keluar dari aplikasi, tidak hanya menghentikan aktifitas lalu mengarahkan ke halaman login
 
- **Sorting Riwayat Tiket:**
  - Pengurutan item tiket di halaman History berdasarkan waktu dan tanggal dari yang paling mendekati waktu sistem/sekarang
  - Item tiket yang masih belum melewati jadwal diurutkan dan ditampilkan diurutan bagian atas
  - Item tiket yang jadwalnya sudah terlewat diurutkan dan ditampilkan diurutan bagian bawah dan mengatur visibility "iv_ticket_status" visible
