INSERT INTO t_role_based_email_master (name) VALUES
('123'),
('abuse'),
('all'),
('arin'),
('arin-abuse'),
('asdf'),
('compliance'),
('contact'),
('contact-us'),
('contacto'),
('contactus'),
('contato'),
('customercare'),
('customerservice'),
('customersupport'),
('custserv'),
('devnull'),
('dns'),
('domain'),
('download'),
('email'),
('everyone'),
('export'),
('facebook'),
('fbl'),
('feedback'),
('ftp'),
('general'),
('hello'),
('help'),
('helpdesk'),
('hi'),
('hola'),
('ispfeedback'),
('ispsupport'),
('list'),
('list-request'),
('listmanager'),
('listproc'),
('listserv'),
('mail'),
('mailbox'),
('maildaemon'),
('request'),
('no-reply'),
('noemail'),
('noreply'),
('null'),
('paypal'),
('phish'),
('phishing'),
('post'),
('postbox'),
('postmaster'),
('privacy'),
('purchase'),
('purchasing'),
('response'),
('ripe-abuse'),
('root'),
('sale'),
('sales'),
('shop'),
('shopping'),
('spam'),
('test'),
('undisclosed-recipients'),
('unsubscribe'),
('www') ON CONFLICT ON CONSTRAINT pk_t_role_based_email_master DO NOTHING;