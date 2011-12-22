

CREATE TABLE "android_metadata" ("locale" TEXT DEFAULT 'en_US');
INSERT INTO "android_metadata" VALUES ('en_US');


CREATE TABLE "serval_developers" (
"id" INTEGER PRIMARY KEY ASC AUTOINCREMENT,
"name" TEXT NOT NULL,
"surname" TEXT NOT NULL
);
INSERT INTO "serval_developers" VALUES (1, 'Paul', 'Gardner-Stephen');
INSERT INTO "serval_developers" VALUES (2, 'Romain', 'Bochet');
INSERT INTO "serval_developers" VALUES (3, 'Jeremy', 'Lakeman');
INSERT INTO "serval_developers" VALUES (4, 'Romana', 'Challans');
