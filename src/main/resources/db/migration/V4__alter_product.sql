-- Drop the foreign key constraint
ALTER TABLE product DROP CONSTRAINT fk_product_category;

-- Drop the column
ALTER TABLE product DROP COLUMN category_id;
