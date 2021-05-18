INSERT INTO IncomingDocument (version, name, registrationNumber, state, sender, addressee) VALUES
('1.0', 'First Incoming', '2020-INC/1', 'new', 'Sender 1', 'Addressee 1'),
('1.1', 'First Incoming 3', '2020-INC/1', 'new', 'Sender 1', 'Addressee 2'),
('1.0', 'Second Incoming', '2020-INC/2', 'new', 'Sender 2', 'Addressee 1'),
('1.0', 'Third Incoming', '2020-INC/3', 'new', 'Sender 1', 'Addressee 2');

INSERT INTO InternalDocument (version, name, registrationNumber, state, executor) VALUES
('1.0', 'First Internal', '2020-INT/1', 'new', 'Addressee 1'),
('1.1', 'First Internal 2', '2020-INT/1', 'new', 'Addressee 2'),
('1.0', 'Second Internal', '2020-INT/2', 'new', 'Addressee 1'),
('1.0', 'Third Internal', '2020-INT/3', 'new', 'Addressee 2');
