INSERT INTO app_info (key, value) VALUES ('env', 'prod'), ('desc', 'Production DB')
ON CONFLICT (key) DO NOTHING;

