-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 06, 2022 lúc 03:09 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quan_ly_sinh_vien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `classes`
--

CREATE TABLE `classes` (
  `cl_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cl_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `_create_at` datetime DEFAULT current_timestamp(),
  `_update_at` datetime DEFAULT current_timestamp(),
  `cl_state` int(11) DEFAULT NULL,
  `_state` int(11) DEFAULT NULL,
  `max` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `classes`
--

INSERT INTO `classes` (`cl_id`, `cl_name`, `_create_at`, `_update_at`, `cl_state`, `_state`, `max`) VALUES
('7i8BKjbda6ylaTTeA3U1bxIWzUd43FqD1gZZRYKjko8ETz', 'hi', '2022-08-05 20:46:15', '2022-08-05 20:46:15', 0, 0, 12),
('BNDYo71lezOBX4bkHa32ujzfeefa69fPG9dQMUK6Uad4fD', 'K34A5DL1', '2022-07-16 20:22:30', '2022-07-16 20:22:30', 1, 0, 20),
('ElKiyaEGXX0UE8uRwBCAmwucOBcfQ0t8vyDeTDKi54A7fn', 'K32DL1', '2022-08-05 14:53:27', '2022-08-05 14:53:27', 0, 0, 10),
('ePUqFXxErNu8YOewxWbNMfzxf2MH0UwFWxWFI0aPorpPqd', 'K35B2DL', '2022-07-17 12:45:35', '2022-07-17 12:45:35', 0, 0, 40),
('HxvBnWpzhwGZ0ulltCFnur2gpyx4FoZnnZgnH2irrpdFyE', 'K34B3DL', '2022-07-16 19:20:49', '2022-07-16 19:20:49', 0, 0, 32),
('Krjxnr69Oy0HPrM0kjUmvM1GCmenGdCBeq4yNhwkpAr6OH', 'K34A3DL', '2022-07-17 12:46:39', '2022-07-17 12:46:39', 0, 0, 40),
('NBaziBBeEIf7rwxaTlDgAH549teZ9k0OFm28Y4Avor5jkI', 'K34B5DL', '2022-08-05 14:56:16', '2022-08-05 14:56:16', 1, 1, 12),
('o5nhdRB3gZ0GABwXiexkBihd34eQ8vZK1OoeQjf5TWXmrC', 'K35B1DL', '2022-07-16 20:21:13', '2022-07-16 20:21:13', 0, 0, 1),
('oX4wDW5x64ZUvZOD62EiytbO8vlEcZ3OGd2PHOAReAWit4', 'K34A4DL', '2022-07-17 14:10:01', '2022-07-17 14:10:01', 1, 1, 50),
('Tx7hgwucfyBbqgxxTbx7O1UzFIF0igEFNpZR6oEMN91Yke', 'K35B2DL', '2022-07-16 20:23:36', '2022-07-16 20:23:36', 1, 1, 40),
('ug4mO6zWOWMDFpBT6CwEPr47OaOGKHxItWXi5khWqQOyfM', 'K35A3DL', '2022-07-18 15:17:04', '2022-07-18 15:17:04', 1, 1, 50),
('vKYDfYY7Fg5fEam53NPcKclUkRKZB8bH6yQNEQT2bxnqZj', 'K34B1DL', '2022-07-16 19:11:55', '2022-07-16 19:11:55', 0, 0, 40),
('xHFdmx5Wbr9juzd13h6ljoF04UMb1TepiUCTQRICK1TW36', 'K34B2DL', '2022-07-16 19:14:10', '2022-07-16 19:14:10', 1, 1, 40),
('XtHrwoddf2MkXEutR7v7DDpdvCMC1ynGXuKcR0DidMlc2H', 'K34A1DL', '2022-07-16 20:24:21', '2022-07-16 20:24:21', 1, 1, 40),
('ypEqZYGnkWT410612MvqpvDbMHPwbvZaXZTTZOvIzZNBhy', 'K34B3DL', '2022-07-17 14:12:44', '2022-07-17 14:12:44', 0, 0, 1),
('zj3giywXduYXawrpl7F3xkKEZu0q7fv8Pqq1ZgGoi4H30q', 'K34A2DL', '2022-07-17 12:46:07', '2022-07-17 12:46:07', 1, 1, 40);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `scores`
--

CREATE TABLE `scores` (
  `sc_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_student_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `_subject_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sc_score` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sc_state` int(11) DEFAULT NULL,
  `_state` int(11) DEFAULT NULL,
  `_create_at` datetime DEFAULT current_timestamp(),
  `_update_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `scores`
--

INSERT INTO `scores` (`sc_id`, `_student_id`, `_subject_id`, `sc_score`, `sc_state`, `_state`, `_create_at`, `_update_at`) VALUES
('0CttCvjhRbpF75Ol9NywYvuYjwerATtho4DrGQ9Z5Iw0gC', 'u923MQkzTa8ey7MqOW0ZOa1CYmtbHMkwol54oz0qX2Ar2r', '7CKYPM0GzHb2RFGEDWgbgaTAG2eKI8O7Bxe0W9t8XABhwu', '7.78', 0, 0, '2022-07-22 15:51:42', '2022-07-22 15:51:42'),
('0eTe3j72xIFdDdv3GaHtRlYAk1W8k82YOBp6cz1M4GkecR', 'u923MQkzTa8ey7MqOW0ZOa1CYmtbHMkwol54oz0qX2Ar2r', 'alQUcR5b445qOGWMREAP2a2yyK6x35jlWl6behoA88WQAA', '7.44', 0, 0, '2022-08-04 08:49:46', '2022-08-04 08:49:46'),
('4EYrpGKcZ0E08YFrAB2NnprMO2dlyoM4igkhf0BOX5D8M5', 'uXQF9atp45hwM1e7dATGMhb3Bx1mviaM46AKwB7I4fD5Ej', 'alQUcR5b445qOGWMREAP2a2yyK6x35jlWl6behoA88WQAA', '7.5', 1, 1, '2022-08-04 09:24:53', '2022-08-04 09:24:53'),
('4HwrwGYGYqKEfKtmB3Q7Fvi6B2l5ezpFUyH1lUFGnjMGIi', 'Q6we8eeF1vi', 'alQUcR5b445qOGWMREAP2a2yyK6x35jlWl6behoA88WQAA', '6.76', 1, 1, '2022-08-04 08:50:19', '2022-08-04 08:50:19'),
('ccqi6evga9WIPWoiiiaogtNMbua7jvzrMvTw6fXQ2Zyzt3', 'BeeCYk9Qa8W', 'alQUcR5b445qOGWMREAP2a2yyK6x35jlWl6behoA88WQAA', '9', 0, 0, '2022-07-20 00:29:21', '2022-07-20 00:29:21'),
('Gur15n9AYaTr13XkrGK0tzvE18Bg0Me7o6YUevynTGo4mc', 'cejxT7KkPzXKbChNndjCOBMQjaNKpIP4tHCC0uCW9lUejP', 'alQUcR5b445qOGWMREAP2a2yyK6x35jlWl6behoA88WQAA', '8', 0, 0, '2022-07-21 16:00:06', '2022-07-21 16:00:06'),
('hBTW3nlQokduowYp990FR9W7m8tlhqA4ta7cmMtabiColW', 'cejxT7KkPzXKbChNndjCOBMQjaNKpIP4tHCC0uCW9lUejP', '7CKYPM0GzHb2RFGEDWgbgaTAG2eKI8O7Bxe0W9t8XABhwu', '8.9', 1, 1, '2022-07-22 12:10:26', '2022-07-22 12:10:26'),
('ihrjncM8abp1Hvcw5mR4EHmjY3oq78aA3Aqj1ArMZRGnxX', 'uXQF9atp45hwM1e7dATGMhb3Bx1mviaM46AKwB7I4fD5Ej', 'Fj570DFtMND8W7fQ19Cei7Eiqatqh3GgKbuondoTUDrWeQ', '8.9', 1, 1, '2022-08-04 09:26:24', '2022-08-04 09:26:24'),
('IP3cOHWlHmDXOCrue8eeYY1OGc9MADtCeGl9bBXZ0GZBiR', '25OrWrojujmHbh5RtRmfHmcRaZ5BtGIigjCH4eKnR00k2G', '2nGltaHioATYNB2Z5MzvjGc8aYYjq19FYkzgrwA8Krjwm8', '10.2', 0, 0, '2022-08-05 21:42:01', '2022-08-05 21:42:01'),
('ix43kuYZcwHGnKd4nUqHlWwACMK94BFkw6A4A64pKImo0a', 'Q6we8eeF1vi', '7CKYPM0GzHb2RFGEDWgbgaTAG2eKI8O7Bxe0W9t8XABhwu', '10', 1, 1, '2022-07-22 15:50:38', '2022-07-22 15:50:38'),
('peqbq0ZFRGvqiXrIvXWMRg3ZB9yXwXux7TF5OoeKemeMo8', 'uXQF9atp45hwM1e7dATGMhb3Bx1mviaM46AKwB7I4fD5Ej', 'Fj570DFtMND8W7fQ19Cei7Eiqatqh3GgKbuondoTUDrWeQ', '9', 1, 1, '2022-07-21 15:57:28', '2022-07-21 15:57:28'),
('Z6ENvecXMwdl9FQbGwl9fxRKZCfmnxAiNXjy8gEn8iq8gj', 'wGNrgZkZ7gZ', 'Fj570DFtMND8W7fQ19Cei7Eiqatqh3GgKbuondoTUDrWeQ', '8', 1, 1, '2022-07-21 15:55:05', '2022-07-21 15:55:05');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `students`
--

CREATE TABLE `students` (
  `st_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `st_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `st_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `st_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `st_phone` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `_class_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `st_gender` int(11) DEFAULT NULL,
  `_create_at` datetime DEFAULT current_timestamp(),
  `_update_at` datetime DEFAULT current_timestamp(),
  `st_state` int(11) DEFAULT NULL,
  `_state` int(11) DEFAULT NULL,
  `st_card` varchar(12) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `st_avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `students`
--

INSERT INTO `students` (`st_id`, `st_name`, `st_email`, `st_address`, `st_phone`, `_class_id`, `st_gender`, `_create_at`, `_update_at`, `st_state`, `_state`, `st_card`, `st_avatar`, `birthday`) VALUES
('25OrWrojujmHbh5RtRmfHmcRaZ5BtGIigjCH4eKnR00k2G', 'Vũ Tuấn Kiệt', 'aa1@gmail.com', 'hà nội', '0987654321', 'BNDYo71lezOBX4bkHa32ujzfeefa69fPG9dQMUK6Uad4fD', 1, '2022-08-05 17:47:01', '2022-08-05 17:47:01', 1, 1, '001202038165', '20220805_17-6-game-nintendo-switch.jpg', '2022-07-31 00:00:00'),
('BeeCYk9Qa8W', 'Vũ Tuấn Kiệt', 'w@gmail.com', 'hà nội', '0987654321', 'Tx7hgwucfyBbqgxxTbx7O1UzFIF0igEFNpZR6oEMN91Yke', 1, '2022-07-18 12:07:32', '2022-07-18 12:07:32', 0, 0, '001202038162', '20220718_13-avatar_vutuankiet.jpg', '2022-07-29 00:00:00'),
('cejxT7KkPzXKbChNndjCOBMQjaNKpIP4tHCC0uCW9lUejP', 'Nguyễn Văn Duy', 'nvd@gmail.com', 'Ha Noi', '0336353365', 'Tx7hgwucfyBbqgxxTbx7O1UzFIF0igEFNpZR6oEMN91Yke', 1, '2022-07-21 15:59:42', '2022-07-21 15:59:42', 0, 0, '001020304050', '20220721_15-2880401.jpg', '2020-05-07 00:00:00'),
('Q6we8eeF1vi', 'Vũ Tuấn Kiệt', 'admin@gmail.com', 'hà nội', '0987654322', 'oX4wDW5x64ZUvZOD62EiytbO8vlEcZ3OGd2PHOAReAWit4', 0, '2022-07-18 14:13:24', '2022-07-18 14:13:24', 1, 1, '001202038164', '20220718_14-2880401.jpg', '2022-04-12 00:00:00'),
('u2pucv3y6P5BCym0ydOo9CqCX2Uwgo2j9MNOAHcfa2ewtj', 'Dang Duy Khanh', 'k@gmail.com', 'ha noi', '0376658437', 'oX4wDW5x64ZUvZOD62EiytbO8vlEcZ3OGd2PHOAReAWit4', 0, '2022-07-19 11:32:12', '2022-07-19 11:32:12', 1, 1, '031202006969', '20220719_11-a.jpg', '2002-12-20 00:00:00'),
('u923MQkzTa8ey7MqOW0ZOa1CYmtbHMkwol54oz0qX2Ar2r', 'Vũ Tuấn Kiệt', 'ad@gmail.com', 'hà nội', '0987654322', 'XtHrwoddf2MkXEutR7v7DDpdvCMC1ynGXuKcR0DidMlc2H', 0, '2022-07-19 14:22:16', '2022-07-19 14:22:16', 1, 1, '001202038166', '20220719_14-2880401.jpg', '2022-06-30 00:00:00'),
('uXQF9atp45hwM1e7dATGMhb3Bx1mviaM46AKwB7I4fD5Ej', 'Kiều Trí Lăng', 'ktl@gmail.com', 'Ha Noi', '0336353366', 'xHFdmx5Wbr9juzd13h6ljoF04UMb1TepiUCTQRICK1TW36', 1, '2022-07-21 15:56:48', '2022-07-21 15:56:48', 1, 1, '001202033452', '20220721_15-2880401.jpg', '2002-02-04 00:00:00'),
('wGNrgZkZ7gZ', 'Dang Duy Khanh', 'a@gmail.com', 'hà nội', '0987654321', 'Tx7hgwucfyBbqgxxTbx7O1UzFIF0igEFNpZR6oEMN91Yke', 0, '2022-07-18 16:28:35', '2022-07-18 16:28:35', 1, 1, '001202038161', '20220718_16-6-game-nintendo-switch.jpg', '2002-02-01 00:00:00'),
('Xq59cPEKxPtdp5AaDQ02c050YixEkEA1WqrmyE9aH5PWzF', 'Vũ Tuấn Kiệt', 'aa@gmail.com', 'hà nội', '0987654321', 'BNDYo71lezOBX4bkHa32ujzfeefa69fPG9dQMUK6Uad4fD', 1, '2022-08-05 17:45:55', '2022-08-05 17:45:55', 1, 1, '001202038121', '20220805_17-10-game-do-hoa-nintendo-switch.jpg', '2022-08-03 00:00:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `subjects`
--

CREATE TABLE `subjects` (
  `sj_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sj_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `_create_at` datetime DEFAULT current_timestamp(),
  `_update_at` datetime DEFAULT current_timestamp(),
  `sj_state` int(11) DEFAULT NULL,
  `_state` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `subjects`
--

INSERT INTO `subjects` (`sj_id`, `sj_name`, `_create_at`, `_update_at`, `sj_state`, `_state`) VALUES
('2nGltaHioATYNB2Z5MzvjGc8aYYjq19FYkzgrwA8Krjwm8', 'ssssss', '2022-08-05 17:54:32', '2022-08-05 17:54:32', 1, 1),
('3BrxhnPX2oMePUFXPf4WagBvPjkYndYEqzOemkcM1nckBa', 'a', '2022-08-05 17:53:46', '2022-08-05 17:53:46', 1, 1),
('7CKYPM0GzHb2RFGEDWgbgaTAG2eKI8O7Bxe0W9t8XABhwu', 'Asp.net', '2022-07-22 12:09:44', '2022-07-22 12:09:44', 1, 0),
('A8lCj5NgPZeYMNNnreOd5qOZ09xAcmQkpterChNeEFlaat', 'ass', '2022-08-05 17:54:16', '2022-08-05 17:54:16', 1, 1),
('alQUcR5b445qOGWMREAP2a2yyK6x35jlWl6behoA88WQAA', 'PHP', '2022-07-19 16:08:03', '2022-07-19 16:08:03', 1, 0),
('Fj570DFtMND8W7fQ19Cei7Eiqatqh3GgKbuondoTUDrWeQ', 'Java', '2022-07-19 16:10:34', '2022-07-19 16:10:34', 1, 0),
('hjKEKCc3ix6O5ljuMXnwA04bW6hN5TKewFlWDZd1iBdX7I', 'sssss', '2022-08-05 17:54:43', '2022-08-05 17:54:43', 1, 1),
('jee88fl05RhZEoiOFmp4vOHmYWUztYTvYoUWyxY44MAegr', 'bswe', '2022-08-05 17:54:09', '2022-08-05 17:54:09', 1, 1),
('jWYDtpMf7tj3EZrB2zb55UzrFY8qDujR5qreOI3dKHnQzc', 'sss', '2022-08-05 17:54:25', '2022-08-05 17:54:25', 1, 1),
('RpjGIuzzazm9Dpy4bzDy2xY4uPKNh04nfvEAK9Qc9vPkOT', 'bbbb', '2022-08-05 17:54:01', '2022-08-05 17:54:01', 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `us_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `us_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `us_password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `valid_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`us_id`, `us_email`, `us_password`, `valid_token`) VALUES
('MTIzNDU2Nzg5MGtrYWFhY2FzY2Fh', 'dangkhanh.dev@gmail.com', 'MTMxMjIwMDJrQA==', NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`cl_id`);

--
-- Chỉ mục cho bảng `scores`
--
ALTER TABLE `scores`
  ADD PRIMARY KEY (`sc_id`),
  ADD KEY `_student_id` (`_student_id`),
  ADD KEY `_subject_id` (`_subject_id`);

--
-- Chỉ mục cho bảng `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`st_id`),
  ADD KEY `_class_id` (`_class_id`);

--
-- Chỉ mục cho bảng `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`sj_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`us_id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `scores`
--
ALTER TABLE `scores`
  ADD CONSTRAINT `scores_ibfk_1` FOREIGN KEY (`_student_id`) REFERENCES `students` (`st_id`),
  ADD CONSTRAINT `scores_ibfk_2` FOREIGN KEY (`_subject_id`) REFERENCES `subjects` (`sj_id`);

--
-- Các ràng buộc cho bảng `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`_class_id`) REFERENCES `classes` (`cl_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
