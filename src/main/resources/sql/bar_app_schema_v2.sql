
CREATE TABLE items (
  item_id int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
  name varchar(50) NOT NULL UNIQUE,
  manufacturer varchar(50),
  calories int(10),
  type varchar(50),
  CONSTRAINT chk_Type CHECK (type IN ('beer', 'food', 'soft_drink'))
);

CREATE TABLE bills (
  bill_id int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
  date datetime,
  tip numeric(9,2),
  tax numeric(2,2) DEFAULT '0.07',
  total numeric(9,2)
);

CREATE TABLE drinkers (
  drinker_id int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
  name varchar(50) NOT NULL,
  phone varchar(12),
  city varchar(50),
  address varchar(255),
  state varchar(2)
);

CREATE TABLE bars (
  bar_id int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
  name varchar(50) NOT NULL UNIQUE,
  license varchar(7) NOT NULL UNIQUE,
  city varchar(50),
  state varchar(2),
  address varchar(255),
  phone varchar(12)
);

CREATE TABLE days (
  day_id int NOT NULL AUTO_INCREMENT UNIQUE,
  name varchar(10) PRIMARY KEY NOT NULL UNIQUE,
  CONSTRAINT chk_Name CHECK (name IN ('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'))
);

CREATE TABLE orders (
  order_number int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
  bill_id int NOT NULL,
  drinker_id int NOT NULL,
  item_id int NOT NULL,
  quantity int,
  order_date datetime,
  FOREIGN KEY(bill_id)
      REFERENCES bills (bill_id)
      ON DELETE CASCADE,
  FOREIGN KEY(drinker_id)
      REFERENCES drinkers (drinker_id)
      ON DELETE CASCADE,
  FOREIGN KEY(item_id)
      REFERENCES items (item_id)
      ON DELETE CASCADE
);

CREATE TABLE sells (
  bar_id int NOT NULL,
  item_id int NOT NULL,
  price numeric(9,2),
  FOREIGN KEY(bar_id)
      REFERENCES bars (bar_id)
      ON DELETE CASCADE,
  FOREIGN KEY(item_id)
      REFERENCES items (item_id)
      ON DELETE CASCADE
);

CREATE TABLE likes (
  drinker_id int NOT NULL,
  item_id int NOT NULL,
  FOREIGN KEY(drinker_id)
      REFERENCES drinkers (drinker_id)
      ON DELETE CASCADE,
  FOREIGN KEY(item_id)
      REFERENCES items (item_id)
      ON DELETE CASCADE
);

CREATE TABLE opens (
  bar_id int NOT NULL,
  day_name varchar(10),
  open_time time,
  close_time time,
  FOREIGN KEY(bar_id)
      REFERENCES bars (bar_id)
      ON DELETE CASCADE,
  FOREIGN KEY(day_name)
      REFERENCES days (name)
      ON DELETE CASCADE
);

CREATE TABLE issues (
  bar_id int NOT NULL,
  bill_id int NOT NULL,
  FOREIGN KEY(bar_id)
      REFERENCES bars (bar_id)
      ON DELETE CASCADE,
  FOREIGN KEY(bill_id)
      REFERENCES bills (bill_id)
      ON DELETE CASCADE
);

CREATE TABLE pays (
  bill_id int NOT NULL,
  drinker_id int NOT NULL,
  paid_date datetime,
  total numeric(9,2),
  FOREIGN KEY (bill_id)
    REFERENCES bills (bill_id)
    ON DELETE CASCADE,
  FOREIGN KEY(drinker_id)
    REFERENCES drinkers (drinker_id)
    ON DELETE CASCADE
);

CREATE TABLE frequents (
  bar_id int NOT NULL,
  drinker_id int NOT NULL,
  FOREIGN KEY(bar_id)
      REFERENCES bars (bar_id)
      ON DELETE CASCADE,
  FOREIGN KEY(drinker_id)
      REFERENCES drinkers (drinker_id)
      ON DELETE CASCADE
);
