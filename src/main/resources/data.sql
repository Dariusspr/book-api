INSERT INTO category (id, title)
VALUES (1, 'Fiction'),
       (2, 'Non-Fiction'),
       (3, 'Science Fiction'),
       (4, 'Fantasy'),
       (5, 'Biography'),
       (6, 'Mystery'),
       (7, 'Self-Help'),
       (8, 'Historical Fiction'),
       (9, 'Romance'),
       (10, 'Thriller');

INSERT INTO author (id, first_name, last_name)
VALUES (1, 'John', 'Doe'),
       (2, 'Jane', 'Smith'),
       (3, 'Emily', 'Johnson'),
       (4, 'Michael', 'Brown'),
       (5, 'Sarah', 'Davis'),
       (6, 'David', 'Wilson'),
       (7, 'Laura', 'Miller'),
       (8, 'Chris', 'Moore'),
       (9, 'Anna', 'Taylor'),
       (10, 'Robert', 'Anderson'),
       (11, 'Jessica', 'Thomas'),
       (12, 'Daniel', 'Jackson');

INSERT INTO book (isbn, category_id, title, published_year, pages)
VALUES ('9783161484100', 1, 'The Great Adventure', 2021, 350),
       ('9781234567897', 2, 'The Truth About Everything', 2019, 275),
       ('9780123456789', 3, 'Journey to the Stars', 2020, 400),
       ('9780987654321', 4, 'Magic and Mayhem', 2019, 500),
       ('9781234987653', 5, 'Life of a Legend', 2018, 320),
       ('9783456789012', 6, 'Whodunit: A Mystery', 2004, 280),
       ('9781234567895', 7, 'Better You, Better Life', 2021, 220),
       ('9780987654325', 8, 'Echoes of the Past', 2022, 300),
       ('9789876543210', 9, 'Love in the Time of Chaos', 2023, 320),
       ('9786543210987', 10, 'Chasing Shadows', 1999, 250),
       ('9784321098765', 7, 'The Art of Storytelling', 2021, 180),
       ('9783210987654', 5, 'Words of Wisdom', 1954, 140),
       ('1283210987654', 6, 'No author.', 1900, 10);

INSERT INTO book_author (book_isbn, author_id)
VALUES ('9783161484100', 1),
       ('9781234567897', 2),
       ('9780123456789', 3),
       ('9780987654321', 4),
       ('9781234987653', 5),
       ('9783456789012', 6),
       ('9781234567895', 7),
       ('9780987654325', 8),
       ('9789876543210', 9),
       ('9786543210987', 10),
       ('9784321098765', 11),
       ('9783210987654', 12),
       ('9783161484100', 3),
       ('9781234567897', 1),
       ('9789876543210', 2),
       ('9786543210987', 9);


INSERT INTO review (id, book_isbn, rating, comment)
VALUES (1, '9783161484100', 5, 'An exhilarating read from start to finish!'),
       (2, '9781234567897', 4, 'Very informative and engaging.'),
       (3, '9780123456789', 3, 'Interesting concept but slow in the middle.'),
       (4, '9780987654321', 5, 'Loved the characters and the world-building!'),
       (5, '9781234987653', 4, 'Inspiring and well-written.'),
       (6, '9783456789012', 2, 'The plot twist was predictable.'),
       (7, '9781234567895', 5, 'A must-read for anyone looking to improve themselves.'),
       (8, '9780987654325', 4, 'An engaging historical narrative with rich details.'),
       (9, '9789876543210', 5, 'A beautiful exploration of love and chaos.'),
       (10, '9786543210987', 4, 'A gripping thriller that kept me on the edge of my seat.'),
       (11, '9784321098765', 2, 'Thought-provoking but could have been more concise.'),
       (12, '9783210987654', 5, 'A collection of wisdom that everyone should read.'),
       (13, '9789876543210', 5, 'An unforgettable love story with profound insights.'),
       (14, '9786543210987', 4, 'Fast-paced and thrilling from beginning to end.'),
       (15, '9786543210987', 1, 'Predictable from beginning to end.'),
       (16, '9781234567897', 2, 'Overrated and not what I expected.'),
       (17, '9780123456789', 1, 'Boring plot and flat characters.'),
       (18, '9780987654321', 3, 'Decent, but I’ve read better fantasy novels.'),
       (19, '9781234987653', 2, 'Couldn’t connect with the story or characters.'),
       (20, '9783456789012', 1, 'Not a mystery at all; it was quite dull.'),
       (21, '9781234567895', 3, 'Some good tips, but mostly common sense.'),
       (22, '9780987654325', 2, 'Too much detail, I lost interest quickly.'),
       (23, '9789876543210', 3, 'Interesting premise, but the execution was lacking.'),
       (24, '9786543210987', 2, 'Not thrilling enough; I expected more action.'),
       (25, '9783161484100', 5, 'An absolute masterpiece, highly recommend!'),
       (26, '9781234567897', 4, 'Well-researched and compelling.'),
       (27, '9780123456789', 4, 'Exciting but could use more character depth.'),
       (28, '9780987654321', 5, 'Fantastic storytelling with great pacing!'),
       (29, '9781234987653', 3, 'A good read, but felt a bit rushed at times.'),
       (30, '9783456789012', 5, 'Loved the twists and turns throughout!'),
       (31, '9781234567895', 4, 'Practical advice, worth a read.'),
       (32, '9780987654325', 3, 'Enjoyable, but not memorable.'),
       (33, '9789876543210', 4, 'A beautifully written story that resonates.'),
       (34, '9786543210987', 1, 'Disappointing conclusion to an intriguing plot.'),
       (35, '9784321098765', 5, 'Every writer should read this book.'),
       (36, '9783210987654', 4, 'Timeless wisdom delivered elegantly.');
