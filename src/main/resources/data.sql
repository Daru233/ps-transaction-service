-- Sample test transactions for Postman and H2 Console

INSERT INTO transactions (
    id, user_id, amount, currency, recipient_account, description, status, created_at
) VALUES (
    '11111111-1111-1111-1111-111111111111',
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    100.00,
    'GBP',
    'TESTACCOUNT111',
    'Payment for invoice #INV001',
    'PENDING',
    CURRENT_TIMESTAMP
);

INSERT INTO transactions (
    id, user_id, amount, currency, recipient_account, description, status, created_at
) VALUES (
    '22222222-2222-2222-2222-222222222222',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
    250.50,
    'USD',
    'TESTACCOUNT222',
    'Monthly subscription',
    'APPROVED',
    CURRENT_TIMESTAMP
);

INSERT INTO transactions (
    id, user_id, amount, currency, recipient_account, description, status, created_at
) VALUES (
    '33333333-3333-3333-3333-333333333333',
    'cccccccc-cccc-cccc-cccc-cccccccccccc',
    79.99,
    'EUR',
    'TESTACCOUNT333',
    'Refund for order #1234',
    'DECLINED',
    CURRENT_TIMESTAMP
);
