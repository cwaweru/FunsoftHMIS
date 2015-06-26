-- Table: st_procurement_plan

-- DROP TABLE st_procurement_plan;

CREATE TABLE st_procurement_plan
(
  item_id character varying(100),
  item_description character varying(1000) NOT NULL,
  qty_planned_for numeric(20,2) NOT NULL,
  qty_cumulative numeric(20,2) DEFAULT 0.0,
  estimated_unit_cost numeric(20,2) NOT NULL,
  estimated_total_cost numeric(20,2) NOT NULL DEFAULT 0.00,
  proc_method character varying(300) NOT NULL,
  advertise_tender date,
  open_tender date,
  evaluation date,
  committee_award_approval date,
  notification_of_award date,
  contract_sign_date date,
  time_to_cotract_signature date,
  time_completion_of_contract date,
  proc_department character varying(200) NOT NULL,
  financial_year character varying(200) NOT NULL,
  head character varying(50) NOT NULL,
  subhead character varying(50) NOT NULL,
  subitem character varying(50) NOT NULL,
  vote_no character varying(200) NOT NULL,
  vote_balance numeric(10,2),
  item_code character varying(100) NOT NULL,
  quarter character varying(100) NOT NULL,
  cummulative_price double precision DEFAULT 0.0
)
WITH (
  OIDS=TRUE
);
ALTER TABLE st_procurement_plan
  OWNER TO postgres;

-- Index: st_procurement_plan_item_id_item_description_qty_planned_fo_idx

-- DROP INDEX st_procurement_plan_item_id_item_description_qty_planned_fo_idx;

CREATE INDEX st_procurement_plan_item_id_item_description_qty_planned_fo_idx
  ON st_procurement_plan
  USING btree
  (item_id COLLATE pg_catalog."default" , item_description COLLATE pg_catalog."default" , qty_planned_for , qty_cumulative , estimated_unit_cost , estimated_total_cost , proc_method COLLATE pg_catalog."default" , advertise_tender , open_tender , evaluation , committee_award_approval , notification_of_award , contract_sign_date , time_to_cotract_signature , time_completion_of_contract , proc_department COLLATE pg_catalog."default" , financial_year COLLATE pg_catalog."default" , head COLLATE pg_catalog."default" , subhead COLLATE pg_catalog."default" , subitem COLLATE pg_catalog."default" , vote_no COLLATE pg_catalog."default" , vote_balance , item_code COLLATE pg_catalog."default" , quarter COLLATE pg_catalog."default" );

