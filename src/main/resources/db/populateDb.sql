
DELETE FROM vote;
ALTER TABLE vote AUTO_INCREMENT = 100000;
DELETE FROM booking;
ALTER TABLE booking AUTO_INCREMENT = 100000;
DELETE FROM apartment;
ALTER TABLE apartment AUTO_INCREMENT = 100000;
DELETE FROM apt_type;
ALTER TABLE apt_type AUTO_INCREMENT = 1;
DELETE FROM hotel;
ALTER TABLE hotel AUTO_INCREMENT = 100000;
DELETE FROM user_role;
ALTER TABLE user_role;
DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT = 100000;
DELETE FROM city;
ALTER TABLE city AUTO_INCREMENT = 100000;
DELETE FROM country;
ALTER TABLE country AUTO_INCREMENT = 1;



INSERT INTO country
(name)
VALUES
  ('Afghanistan'),
  ('Algeria'),
  ('Angola'),
  ('Argentina'),
  ('Armenia'),
  ('Australia'),
  ('Austria'),
  ('Azerbaijan'),
  ('Bangladesh'),
  ('Belarus'),
  ('Belgium'),
  ('Bolivia'),
  ('Brazil'),
  ('Bulgaria'),
  ('Burkina Faso'),
  ('Cambodia'),
  ('Cameroon'),
  ('Canada'),
  ('Central African Republic'),
  ('Chile'),
  ('China'),
  ('Colombia'),
  ('Congo Br'),
  ('Congo D.R.'),
  ('Croatia'),
  ('Cuba'),
  ('Czech Republic'),
  ('Denmark'),
  ('Dominican Republic'),
  ('Ecuador'),
  ('Egypt'),
  ('Ethiopia'),
  ('Finland'),
  ('France'),
  ('Georgia'),
  ('Germany'),
  ('Ghana'),
  ('Greece'),
  ('Guatemala'),
  ('Guinea'),
  ('Haiti'),
  ('Honduras'),
  ('Hungary'),
  ('India'),
  ('Indonesia'),
  ('Iran'),
  ('Iraq'),
  ('Israel'),
  ('Italy'),
  ('Ivory Coast'),
  ('Jamaica'),
  ('Japan'),
  ('Jordan'),
  ('Kazakhstan'),
  ('Kenya'),
  ('Kyrgyzstan'),
  ('Latvia'),
  ('Lebanon'),
  ('Liberia'),
  ('Libya'),
  ('Lithuania'),
  ('Madagascar'),
  ('Malawi'),
  ('Malaysia'),
  ('Mali'),
  ('Mexico'),
  ('Moldova'),
  ('Mongolia'),
  ('Morocco'),
  ('Mozambique'),
  ('Myanmar (Burma)'),
  ('Nepal'),
  ('Netherlands'),
  ('Nicaragua'),
  ('Nigeria'),
  ('North Korea'),
  ('Norway'),
  ('Oman'),
  ('Pakistan'),
  ('Panama'),
  ('Paraguay'),
  ('Peru'),
  ('Philippines'),
  ('Poland'),
  ('Portugal'),
  ('Romania'),
  ('Russia'),
  ('Saudi Arabia'),
  ('Senegal'),
  ('Serbia'),
  ('Sierra Leone'),
  ('Singapore'),
  ('Somalia'),
  ('South Africa'),
  ('South Korea'),
  ('Spain'),
  ('Sri Lanka'),
  ('Sudan'),
  ('Sweden'),
  ('Syria'),
  ('Taiwan'),
  ('Tajikistan'),
  ('Tanzania'),
  ('Thailand'),
  ('Tunisia'),
  ('Turkey'),
  ('UAE'),
  ('UK'),
  ('USA'),
  ('Uganda'),
  ('Ukraine'),
  ('Uruguay'),
  ('Uzbekistan'),
  ('Venezuela'),
  ('Vietnam'),
  ('Yemen'),
  ('Zambia'),
  ('Zimbabwe');




INSERT INTO city
(name, country_id)
VALUES
  ('Karachi', 79),
  ('Shanghai', 21),
  ('Mumbai', 44),
  ('Beijing', 21),
  ('Delhi', 44),
  ('Buenos Aires', 4),
  ('Manila Metro', 83),
  ('Seoul', 95),
  ('Sao Paulo', 13),
  ('Moscow', 87),
  ('Jakarta', 45),
  ('Istanbul', 106),
  ('Bangkok', 104),
  ('Tokyo', 52),
  ('Tehran', 46),
  ('New York City', 109),
  ('Kinshasa', 24),
  ('Dhaka', 9),
  ('Lagos', 75),
  ('Cairo', 31),
  ('Lima', 82),
  ('London', 108),
  ('Tianjin', 21),
  ('Bogota', 22),
  ('Ho Chi Minh City', 115),
  ('Hong Kong', 21),
  ('Guangzhou', 21),
  ('Dongguan', 21),
  ('Lahore', 79),
  ('Rio De Janeiro', 13),
  ('Baghdad', 47),
  ('Bangalore', 44),
  ('Surat', 44),
  ('Santiago', 20),
  ('Kolkata', 44),
  ('Shenyang', 21),
  ('Chongqing', 21),
  ('Singapore', 92),
  ('Riyadh', 88),
  ('Luanda', 3),
  ('Harbin', 21),
  ('St Petersburg', 87),
  ('Chennai', 44),
  ('Ahmadabad', 44),
  ('Wuhan', 21),
  ('Yangon', 71),
  ('Sydney', 6),
  ('Chengdu', 21),
  ('Shenzhen', 21),
  ('Nanjing', 21),
  ('Alexandria', 31),
  ('Johannesburg', 94),
  ('Jeddah', 88),
  ('Los Angeles', 109),
  ('Abidjan', 50),
  ('Ankara', 106),
  ('Casablanca', 69),
  ('Yokohama', 52),
  ('Hyderabad', 44),
  ('Melbourne', 6),
  ('Kano', 75),
  ('Busan', 95),
  ('Kabul', 1),
  ('Changchun', 21),
  ('Cape Town', 94),
  ('Guiyang', 21),
  ('Berlin', 36),
  ('Hangzhou', 21),
  ('Addis Ababa', 32),
  ('Pune', 44),
  ('Pyongyang', 76),
  ('Madrid', 96),
  ('Nairobi', 55),
  ('Kanpur', 44),
  ('Kunming', 21),
  ('Surabaya', 45),
  ('Jaipur', 44),
  ('Salvador Da Bahia', 13),
  ('Santo Domingo', 29),
  ('Mashhad', 46),
  ('Chicago', 109),
  ('Zibo', 21),
  ('Kiev', 111),
  ('Durg', 44),
  ('Huludao', 21),
  ('Caracas', 114),
  ('Qingdao', 21),
  ('Changsha', 21),
  ('Rome', 49),
  ('Fuzhou', 21),
  ('Quezon City', 83),
  ('Xian', 21),
  ('Port Harcourt', 75),
  ('Osaka', 52),
  ('Incheon', 95),
  ('Shijiazhuang', 21),
  ('Taipei', 101),
  ('Zhengzhou', 21),
  ('Chittagong', 9),
  ('Toronto', 18),
  ('Ibadan', 75),
  ('Taiyuan', 21),
  ('Cali', 22),
  ('Daegu', 95),
  ('Bandung', 45),
  ('Faisalabad', 79),
  ('Fortaleza', 13),
  ('Baoshan', 21),
  ('Dar Es Salaam', 103),
  ('Zhongshan', 21),
  ('Xiamen', 21),
  ('Chaoyang', 21),
  ('Belo Horizonte', 13),
  ('Nanning', 21),
  ('Havana', 26),
  ('Nagpur', 44),
  ('Omdurman', 98),
  ('Suzhou', 21),
  ('Aleppo', 100),
  ('Lucknow', 44),
  ('Izmir', 106),
  ('Linyi', 21),
  ('Dalian', 21),
  ('Nagoya', 52),
  ('Houston', 109),
  ('Giza', 31),
  ('Medellin', 22),
  ('Khartoum', 98),
  ('Ningbo', 21),
  ('Guayaquil', 30),
  ('Tashkent', 113),
  ('Paris', 34),
  ('Brasilia', 13),
  ('Lanzhou', 21),
  ('Changzhou', 21),
  ('Monterrey', 66),
  ('Baku', 8),
  ('Kowloon', 21),
  ('Bucharest', 86),
  ('Medan', 45),
  ('Tangshan', 21),
  ('Jilin', 21),
  ('Nanchong', 21),
  ('Conakry', 40),
  ('Amman', 53),
  ('Indore', 44),
  ('Sapporo', 52),
  ('Jinan', 21),
  ('Puebla', 66),
  ('Haiphong', 115),
  ('Macheng', 21),
  ('Curitiba', 13),
  ('Nanchang', 21),
  ('Minsk', 10),
  ('Xuzhou', 21),
  ('Kuala Lumpur', 64),
  ('Bamako', 65),
  ('Huzhou', 21),
  ('Patna', 44),
  ('Suzhou Anhui', 21),
  ('Urumqi', 21),
  ('Yantai', 21),
  ('Maracaibo', 114),
  ('Tianmen', 21),
  ('Hamburg', 36),
  ('Shantou', 21),
  ('Basrah', 47),
  ('Hefei', 21),
  ('Tengzhou', 21),
  ('Wuxi', 21),
  ('Sanaa', 116),
  ('Manaus', 13),
  ('Fuyang', 21),
  ('Budapest', 43),
  ('Warsaw', 84),
  ('Mecca', 88),
  ('Ecatepec', 66),
  ('Tripoli', 60),
  ('Vienna', 7),
  ('Suizhou', 21),
  ('Barcelona', 96),
  ('Damascus', 100),
  ('Agra', 44),
  ('Gaozhou', 21),
  ('Taian', 21),
  ('Quito', 30),
  ('Tianshui', 21),
  ('Montreal', 18),
  ('Nashik', 44),
  ('Shangqiu', 21),
  ('Accra', 37),
  ('Perth', 6),
  ('Harare', 118),
  ('Palembang', 45),
  ('Santa Cruz', 12),
  ('Esfahan', 46),
  ('Guadalajara', 66),
  ('Phoenix', 109),
  ('Recife', 13),
  ('Neijiang', 21),
  ('Rawalpindi', 79),
  ('Tangerang', 45),
  ('Brisbane', 6),
  ('Philadelphia', 109),
  ('Kobe', 52),
  ('Hechuan', 21),
  ('Kaohsiung', 101),
  ('Pretoria', 94),
  ('Taizhou', 21),
  ('Algiers', 2),
  ('La Paz', 12),
  ('Pimpri Chinchwad', 44),
  ('Barquisimeto', 114),
  ('Guigang', 21),
  ('Kumasi', 37),
  ('Luoyang', 21),
  ('Mogadishu', 93),
  ('Vadodara', 44),
  ('Quanzhou', 21),
  ('Tbilisi', 35),
  ('Tijuana', 66),
  ('Xintai', 21),
  ('Nanan', 21),
  ('Ouagadougou', 15),
  ('Benghazi', 60),
  ('Kyoto', 52),
  ('Kharkiv', 111),
  ('Kaduna', 75),
  ('Xinyang', 21),
  ('Bhopal', 44),
  ('Rugao', 21),
  ('Anyang', 21),
  ('Fukuoka', 52),
  ('Weifang', 21),
  ('Zhanjiang', 21),
  ('Hyderabad', 79),
  ('Fushun', 21),
  ('Daejeon', 95),
  ('Qiqihaer', 21),
  ('Jianyang', 21),
  ('Yaoundé', 17),
  ('Multan', 79),
  ('Almaty', 54),
  ('Guiping', 21),
  ('Huazhou', 21),
  ('Kampala', 110),
  ('Tabriz', 46),
  ('Gujranwala', 79),
  ('Gwangju', 95),
  ('Belem', 13),
  ('Sofia', 14),
  ('Bursa', 106),
  ('Changde', 21),
  ('Ciudad Juarez', 66),
  ('Ludhiana', 44),
  ('Tongzhou', 21),
  ('Novosibirsk', 87),
  ('Semarang', 45),
  ('Handan', 21),
  ('Kawasaki', 52),
  ('Suining', 21),
  ('Douala', 17),
  ('Karaj', 46),
  ('Liuyang', 21),
  ('Luzhou', 21),
  ('Caloocan', 83),
  ('Thane', 44),
  ('Varanasi', 44),
  ('Hanoi', 115),
  ('Munich', 36),
  ('Davao City', 83),
  ('Porto Alegre', 13),
  ('Taixing', 21),
  ('Bozhou', 21),
  ('San Antonio', 109),
  ('Jinjiang', 21),
  ('Lufeng', 21),
  ('Yongcheng', 21),
  ('Guilin', 21),
  ('Pingdu', 21),
  ('Ahwaz', 46),
  ('Rajkot', 44),
  ('Santiago De Los Caballeros', 29),
  ('Montevideo', 112),
  ('Ekaterinburg', 87),
  ('Baotou', 21),
  ('Cordoba', 4),
  ('Milan', 49),
  ('Lianjiang', 21),
  ('Medina', 88),
  ('Mianyang', 21),
  ('Yiyang', 21),
  ('Barranquilla', 22),
  ('Irbil', 47),
  ('Adelaide', 6),
  ('Anshan', 21),
  ('Ranchi', 44),
  ('Rizhao', 21),
  ('Guarulhos', 13),
  ('Goiania', 13),
  ('Dallas', 109),
  ('Heze', 21),
  ('Nizhniy Novgorod', 87),
  ('San Diego', 109),
  ('Meerut', 44),
  ('La Matanza', 4),
  ('Datong', 21),
  ('Beirut', 58),
  ('Fengcheng', 21),
  ('Ruian', 21),
  ('Laiwu', 21),
  ('Maputo', 70),
  ('Phnom Penh', 16),
  ('Prague', 27),
  ('Pingdingshan', 21),
  ('Yuzhou', 21),
  ('Shiraz', 46),
  ('Cixi', 21),
  ('Allahabad', 44),
  ('Huainan', 21),
  ('Anqiu', 21),
  ('Fuqing', 21),
  ('Maiduguri', 75),
  ('Pikine-guediawaye', 89),
  ('Tegucigalpa', 42),
  ('Tunis', 105),
  ('Valencia', 114),
  ('Amritsar', 44),
  ('Qianjiang', 21),
  ('Bazhong', 21),
  ('Managua', 74),
  ('Leqing', 21),
  ('Saitama', 52),
  ('Belgrade', 90),
  ('Hiroshima', 52),
  ('Dongtai', 21),
  ('Ujung Pandang', 45),
  ('Aurangabad', 44),
  ('Adana', 106),
  ('Guangyuan', 21),
  ('Qidong', 21),
  ('Rosario', 4),
  ('Samara', 87),
  ('Zapopan', 66),
  ('Nezahualcoyotl', 66),
  ('Lubumbashi', 24),
  ('Mosul', 47),
  ('Leon', 66),
  ('Omsk', 87),
  ('Solapur', 44),
  ('Brazzaville', 23),
  ('Bijie', 21),
  ('Haicheng', 21),
  ('Madurai', 44),
  ('Ulsan', 95),
  ('Leshan', 21),
  ('Jabalpur', 44),
  ('Kazan', 87),
  ('Makasar', 45),
  ('Jimo', 21),
  ('Jining', 21),
  ('Lusaka', 117),
  ('Ulaanbaatar', 68),
  ('Yerevan', 5),
  ('Peshawar', 79),
  ('Wafangdian', 21),
  ('Copenhagen', 28),
  ('Mirat', 44),
  ('Chelyabinsk', 87),
  ('Guatemala City', 39),
  ('Shouguang', 21),
  ('Dubai', 107),
  ('Suwon', 95),
  ('Port-au-prince', 41),
  ('Odessa', 111),
  ('Goyang', 95),
  ('Seongnam', 95),
  ('Marrakech', 69),
  ('Taishan', 21),
  ('Rostov On Don', 87),
  ('Dhanbad', 44),
  ('Taichung', 101),
  ('Ezhou', 21),
  ('Campinas', 13),
  ('Faridabad', 44),
  ('Jiangdu', 21),
  ('Benin', 75),
  ('Beiliu', 21),
  ('Gongzhuling', 21),
  ('Changshu', 21),
  ('Qom', 46),
  ('Dnipropetrovsk', 111),
  ('Freetown', 91),
  ('Sendai', 52),
  ('Dakar', 89),
  ('Ufa', 87),
  ('Fuzhou', 21),
  ('Yichun', 21),
  ('Birmingham', 108),
  ('Mudanjiang', 21),
  ('Volgograd', 87),
  ('Perm', 87),
  ('Fes', 69),
  ('Haora', 44),
  ('Jodhpur', 44),
  ('San Jose', 109),
  ('Torreon', 66),
  ('Mexicali', 66),
  ('Subra Al-haymah', 31),
  ('Baoding', 21),
  ('Cologne', 36),
  ('Hezhou', 21),
  ('Calgary', 18),
  ('Sao Luis', 13),
  ('Kitakyushu', 52),
  ('Sao Goncalo', 13),
  ('Ghaziabad', 44),
  ('Visakhapatnam', 44),
  ('Wujiang', 21),
  ('Naples', 49),
  ('Chiba', 52),
  ('Mandalay', 71),
  ('Feicheng', 21),
  ('Brussels', 11),
  ('Vijayawada', 44),
  ('Haimen', 21),
  ('Kathmandu', 72),
  ('Ciudad Guayana', 114),
  ('Safi', 69),
  ('Coimbatore', 44),
  ('Gaziantep', 106),
  ('Weinan', 21),
  ('Soweto', 94),
  ('Maceio', 13),
  ('Agadir', 69),
  ('Krasnoyarsk', 87),
  ('Srinagar', 44),
  ('Detroit', 109),
  ('Turin', 49),
  ('Arequipa', 82),
  ('Mbuji-mayi', 24),
  ('Songzi', 21),
  ('Salé', 69),
  ('Antananarivo', 62),
  ('Laizhou', 21),
  ('Chandigarh', 44),
  ('Zaria', 75),
  ('Abu Dhabi', 107),
  ('Cartagena', 22),
  ('Teresina', 13),
  ('Danyang', 21),
  ('Sholapur', 44),
  ('Hengyang', 21),
  ('Honghu', 21),
  ('Mombasa', 55),
  ('Daye', 21),
  ('Bogor', 45),
  ('Lilongwe', 63),
  ('Duque De Caxias', 13),
  ('Khulna', 9),
  ('Marseille', 34),
  ('Bucheon', 95),
  ('Norfolk', 109),
  ('Panama City', 80),
  ('Voronezh', 87),
  ('Nova Iguacu', 13),
  ('Sakai', 52),
  ('Port Elizabeth', 94),
  ('Saratov', 87),
  ('Benxi', 21),
  ('Haikou', 21),
  ('Stockholm', 99),
  ('Hims', 100),
  ('Kermanshah', 46),
  ('Naucalpan', 66),
  ('Hamhung', 76),
  ('Hohhot', 21),
  ('Padang', 45),
  ('Hamamatsu', 52),
  ('Ottawa', 18),
  ('Ad-dammam', 88),
  ('Niigata', 52),
  ('Liuzhou', 21),
  ('Thiruvananthapuram', 44),
  ('Valencia', 96),
  ('Bengbu', 21),
  ('San Francisco', 109),
  ('Guwahati', 44),
  ('Jacksonville', 109),
  ('Santiago De Queretaro', 66),
  ('Zagreb', 25),
  ('Astana', 54),
  ('Kolwezi', 24),
  ('Hubli', 44),
  ('Daqing', 21),
  ('Nanyang', 21),
  ('Mysore', 44),
  ('Indianapolis', 109),
  ('Natal', 13),
  ('Chisinau', 67),
  ('Toluca', 66),
  ('Lodz', 84),
  ('Sao Bernardo Do Campo', 13),
  ('Zaporizhzhya', 111),
  ('Abuja', 75),
  ('Leeds', 108),
  ('Masqat-matrah', 78),
  ('Tainan', 101),
  ('Bishkek', 56),
  ('Jerusalem', 48),
  ('Amsterdam', 73),
  ('Jixi', 21),
  ('Tiruchchirappalli', 44),
  ('Athens', 38),
  ('Krakow', 84),
  ('Cebu City', 83),
  ('Ankang', 21),
  ('Bandar Lampung', 45),
  ('Malang', 45),
  ('Lviv', 111),
  ('Merida', 66),
  ('Edmonton', 18),
  ('Ogbomosho', 75),
  ('Riga', 57),
  ('Acapulco', 66),
  ('Shizuoka', 52),
  ('Columbus', 109),
  ('Jalandhar', 44),
  ('Xining', 21),
  ('Krasnodar', 87),
  ('La Plata', 4),
  ('Tangier', 69),
  ('Sevilla', 96),
  ('Joao Pessoa', 13),
  ('Okayama', 52),
  ('Chihuahua', 66),
  ('Fuxin', 21),
  ('Gwalior', 44),
  ('Jinzhou', 21),
  ('San Luis Potosi', 66),
  ('Tlalnepantla De Baz', 66),
  ('Palermo', 49),
  ('Oran', 2),
  ('Austin', 109),
  ('Aligarh', 44),
  ('Dushanbe', 102),
  ('Zhangjiakou', 21),
  ('Amravati', 44),
  ('Bulawayo', 118),
  ('Durban', 94),
  ('Memphis', 109),
  ('Baltimore', 109),
  ('Frankfurt', 36),
  ('Aguascalientes', 66),
  ('Bhubaneswar', 44),
  ('Bangui', 19),
  ('Colombo', 97),
  ('Kingston', 51),
  ('Jamshedpur', 44),
  ('Rabat', 69),
  ('Nashville', 109),
  ('Sao Jose Dos Campos', 13),
  ('Cochabamba', 12),
  ('Milwaukee', 109),
  ('Stuttgart', 36),
  ('Washington Dc', 109),
  ('Rotterdam', 73),
  ('Dortmund', 36),
  ('Kaifeng', 21),
  ('Oslo', 77),
  ('Denver', 109),
  ('Helsinki', 33),
  ('Glasgow', 108),
  ('Essen', 36),
  ('Vancouver', 18),
  ('Seattle', 109),
  ('Culiacan', 66),
  ('Bhilai', 44),
  ('Hengyang', 21),
  ('Charlotte', 109),
  ('Las Vegas', 109),
  ('Lisbon', 85),
  ('Oklahoma City', 109),
  ('Kitwe', 117),
  ('Bremen', 36),
  ('Ribeirao Preto', 13),
  ('Asuncion', 81),
  ('Vilnius', 61),
  ('Monrovia', 59),
  ('Bucaramanga', 22),
  ('Portland', 109),
  ('Tucson', 109),
  ('Atlanta', 109),
  ('Sheffield', 108),
  ('Surakarta', 45);







INSERT INTO user (name, email, password)
VALUES
  ('User1', 'user1@yandex.ru', 'password1'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('User3', 'user3@yandex.ru', 'password3');

INSERT INTO user (name, email, password)
VALUES ('Manager', 'manager@gmail.com', 'manager');

INSERT INTO user (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_role (user_id, role) VALUES
  (100000, 'ROLE_USER'),
  (100001, 'ROLE_USER'),
  (100002, 'ROLE_USER'),
  (100003, 'ROLE_HOTEL_MANAGER'),
  (100004, 'ROLE_USER'),
  (100004, 'ROLE_SYSTEM_ADMIN');





INSERT INTO hotel (name, stars, country_id, city_id, address, phone, description, check_in, check_out)
VALUES
  ('HOTEL1', 3, 87, 100009, 'Address1', '89431543453', null, '14:00:00', '12:00:00'),
  ('HOTEL2', 4, 87, 100041, 'Address2', '89431564565', 'Description2', '14:00:00', '12:00:00'),
  ('HOTEL3', null, 111, 100519, 'Address3', '894312223222', 'Description3', '14:00:00', '12:00:00'),
  ('HOTEL4', 4, 111, 100082, 'Address4', null, 'Description4', '14:00:00', '12:00:00'),
  ('HOTEL5', 3, 109, 100570, 'Address5', null, 'Description5', '14:00:00', '12:00:00'),
  ('HOTEL6', 3, 36, 100573, 'Address6', '894312223222', 'Description6', '14:00:00', '12:00:00'),
  ('HOTEL7', 3, 109, 100565, 'Address7', null, 'Description7', '14:00:00', '12:00:00'),
  ('HOTEL8', 3, 13, 100029, 'Address8', null, 'Description8', '14:00:00', '12:00:00'),
  ('HOTEL9', 3, 36, 100552, 'Address9', null, 'Description9', '14:00:00', '12:00:00'),
  ('HOTEL10', 3, 13, 100029, 'Address10', null, 'Description10', '14:00:00', '12:00:00'),
  ('HOTEL11', null, 27, 100313, 'Address11', '89431222367', 'Description11', '14:00:00', '12:00:00'),
  ('HOTEL12', 3, 18, 100099, 'Address12', '894312227546', 'Description12', '14:00:00', '12:00:00'),
  ('HOTEL13', null, 10, 100153, 'Address13', '894312223423', 'Description13', '14:00:00', '12:00:00'),
  ('HOTEL14', 3, 109, 100462, 'Address14', '89431222377', 'Description14', '14:00:00', '12:00:00'),
  ('HOTEL15', 3, 108, 100572, 'Address15', '89431245622', 'Description15', '14:00:00', '12:00:00'),
  ('HOTEL16', 3, 101, 100096, 'Address16', '894312223762', 'Description16', '14:00:00', '12:00:00'),
  ('HOTEL17', 3, 87, 100041, 'Address17', '89431221111', 'Description17', '14:00:00', '12:00:00'),
  ('HOTEL18', 3, 111, 100082, 'Address18', null, 'Description18', '14:00:00', '12:00:00'),
  ('HOTEL19', 3, 109, 100015, 'Address19', null, 'Description19', '14:00:00', '12:00:00'),
  ('HOTEL20', 3, 109, 100015, 'Address20', null, 'Description20', '14:00:00', '12:00:00'),
  ('HOTEL21', 3, 109, 100462, 'Address21', null, 'Description21', '14:00:00', '12:00:00'),
  ('HOTEL22', 3, 27, 100313, 'Address22', null, 'Description22', '14:00:00', '12:00:00');



INSERT INTO apt_type(beds_arrangement, category, person_num)
VALUES
  ('ONE SINGLE BED', 'STANDARD', 1),
  ('TWO SINGLE BEDS', 'STANDARD', 2),
  ('TWO SEPARATE BEDS', 'STANDARD', 2),
  ('TWO SINGLE BEDS', 'SUPERIOR', 2),
  ('TWO SEPARATE BEDS', 'SUPERIOR', 2),
  ('TWO SEPARATE BEDS', 'COMFORT', 2),
  ('THREE-PERSON WITH SEPARATE BEDS', 'FAMILY NUMBER', 3),
  ('THREE-PERSON WITH TWIN BED', 'FAMILY NUMBER', 3),
  ('FOUR-PERSON WITH SEPARATE BEDS', 'FAMILY NUMBER', 4),
  ('FOUR-PERSON WITH TWIN BEDS', 'FAMILY NUMBER', 4),
  ('FIVE-PERSON WITH SEPARATE BEDS', 'FAMILY NUMBER', 5),
  ('FIVE-PERSON WITH TWIN BEDS', 'FAMILY NUMBER', 5),
  ('SIX-PERSON WITH SEPARATE BEDS', 'FAMILY NUMBER', 6),
  ('SIX-PERSON WITH TWIN BEDS', 'FAMILY NUMBER', 6),
  ('FOUR-PERSON WITH SEPARATE BEDS', 'HOSTEL ROOM', 4),
  ('FOUR-PERSON WITH BUNK BEDS', 'HOSTEL ROOM', 4),
  ('FIVE-PERSON WITH SEPARATE BEDS', 'HOSTEL ROOM', 5),
  ('FIVE-PERSON WITH BUNK BEDS', 'HOSTEL ROOM', 5),
  ('SIX-PERSON WITH SEPARATE BEDS', 'HOSTEL ROOM', 6),
  ('SIX-PERSON WITH BUNK BEDS', 'HOSTEL ROOM', 6),
  ('SEVEN-PERSON WITH SEPARATE BEDS', 'HOSTEL ROOM', 7),
  ('SEVEN-PERSON WITH BUNK BEDS', 'HOSTEL ROOM', 7),
  ('EIGHT-PERSON WITH SEPARATE BEDS', 'HOSTEL ROOM', 8),
  ('EIGHT-PERSON WITH BUNK BEDS', 'HOSTEL ROOM', 8),
  ('NINE-PERSON WITH SEPARATE BEDS', 'HOSTEL ROOM', 9),
  ('NINE-PERSON WITH BUNK BEDS', 'HOSTEL ROOM', 9),
  ('TEN-PERSON WITH SEPARATE BEDS', 'HOSTEL ROOM', 10),
  ('TEN-PERSON WITH BUNK BEDS', 'HOSTEL ROOM', 10);



INSERT INTO apartment (apt_type_id, overall_quantity, reserved_quantity, price, hotel_id)
VALUES
  (1, 2, 1, 1200.00, 100000),
  (2, 3, 3, 1500.00, 100000),
  (3, 4, 0, 3290.00, 100001),
  (4, 3, 1, 4600.00, 100002),
  (1, 6, 3, 1200.00, 100003),
  (2, 4, 2, 4200.00, 100004),
  (4, 3, 2, 2200.00, 100005),
  (3, 2, 1, 5600.00, 100005),
  (6, 5, 4, 2300.00, 100006),
  (7, 2, 0, 3500.00, 100006),
  (8, 1, 1, 4400.00, 100007),
  (17, 2, 0, 1500.00, 100008),
  (16, 1, 0, 1100.00, 100009),
  (15, 3, 2, 1400.00, 100009),
  (18, 1, 1, 1250.00, 100009),
  (5, 2, 1, 5250.00, 100010),
  (7, 5, 3, 2400.00, 100011),
  (3, 4, 2, 3450.00, 100011),
  (2, 3, 2, 1290.00, 100012);



INSERT INTO booking (active, date_added, in_date, out_date, sum, person_num, extra_beds, user_id, apartment_id, apartment_hotel_id)
VALUES
  (1, '2016-05-12 16:17:00', '2017-05-23 14:00:00', '2017-05-28 12:00:00', 6000.00, 1, 0, 100000, 100000, 100000),
  (1, '2017-01-23 11:21:00', '2017-04-14 14:00:00', '2017-04-17 12:00:00', 4500.00, 2, 0, 100001, 100001, 100000),
  (1, '2017-03-08 19:25:00', '2017-06-07 14:00:00', '2017-06-30 12:00:00', 3200.00, 2, 0, 100002, 100002, 100001),
  (1, '2017-05-02 10:43:00', '2017-05-21 14:00:00', '2017-05-23 12:00:00', 10000.00, 2, 1, 100000, 100003, 100002),
  (0, '2017-02-15 16:35:00', '2017-05-21 14:00:00', '2017-05-28 12:00:00', 31200.00, 4, 0, 100004, 100004, 100003),
  (0, '2017-03-03 17:33:00', '2017-05-11 14:00:00', '2017-05-15 12:00:00', 16800.00, 2, 1, 100000, 100005, 100004),
  (0, '2017-04-08 13:38:00', '2017-06-03 14:00:00', '2017-06-09 12:00:00', 13200.00, 2, 0, 100001, 100006, 100005),
  (0, '2017-05-11 14:43:00', '2017-06-02 14:00:00', '2017-07-04 12:00:00', 184800.00, 2, 2, 100002, 100007, 100005),
  (0, '2017-01-19 12:24:00', '2017-02-22 14:00:00', '2017-02-25 12:00:00', 6900.00, 2, 0, 100004, 100008, 100006);



INSERT INTO vote (rate, user_comment, date_added, user_id, hotel_id)
VALUES
  (9.5, 'User Vote comment  1', '2017-06-16 19:32:00', 100000, 100000),
  (6.5, 'User Vote comment  2', '2017-05-04 09:45:00', 100001, 100000),
  (5.5, 'User Vote comment  3', '2017-06-10 13:45:00', 100002, 100001),
  (7.0, 'User Vote comment  4', '2017-05-29 14:23:00', 100000, 100002),
  (7.5, 'User Vote comment  5', '2017-06-11 10:05:00', 100004, 100003),
  (8.0, 'User Vote comment  6', '2017-05-17 22:34:00', 100000, 100004),
  (8.5, 'User Vote comment  7', '2017-06-14 23:16:00', 100001, 100005);






