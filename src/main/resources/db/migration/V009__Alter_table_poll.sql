ALTER TABLE polls
    ALTER COLUMN create_at SET DEFAULT CURRENT_DATE,
    ALTER COLUMN up_to_date DROP NOT NULL;