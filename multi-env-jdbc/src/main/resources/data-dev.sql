INSERT INTO app_info (key, value) VALUES ('env', 'dev'), ('desc', 'Development DB')
ON CONFLICT (key) DO NOTHING;
