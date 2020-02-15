import React from 'react';
import RecordView, { Record, RecordError } from './RecordView';

const Section: React.FC<{ header: string }> = ({ header, children }) => {
  return (
    <>
      <div style={{
        color: 'white',
        padding: '5px',
        minWidth: '90%',
        textAlign: 'center',
        fontSize: '1.3em',
        backgroundColor: 'black',
      }}>
        {header}
      </div>
      <div style={{ padding: '5px' }}>
        {children}
      </div>
    </>
  )
};

const MessageContentView: React.FC<{ errors: RecordError[] }> = ({ errors }) => {
  return (
    <Section header="Message">
      <div style={{
        color: 'red',
        minHeight: '10vh',
        marginLeft: '5%',
        marginRight: '5%',
        display: 'flex',
        flexDirection: 'row',
      }}>
        <div style={{
          fontSize: '3em',
          padding: '1%',
          paddingLeft: '10%',
          paddingRight: '10%',
          textAlign: 'center',
          fontFamily: 'serif',
        }}>
          ERROR!
        </div>
        <div style={{
          display: 'flex',
          flexDirection: 'column',
        }}>
          {errors.map((error, index) => (
            <span key={index}>
              {error.reason}
            </span>
          ))}
        </div>
      </div>
    </Section>
  );
};

const DetailContentView: React.FC<{ errors: RecordError[] }> = ({ errors }) => {
  const records: Record[] = [
    {
      kind: 'FILE HEADER RECORD',
      count: '1',
      destination: '101000019',
      origin: '741258964',
      originName: 'THE FAB FOUR CORP',
    },
    {
      kind: 'BATCH HEADER RECORD',
      count: '5',
      companyName: 'STRAWBERRYFIELDS',
      companyId: '741258964',
      effectiveDate: '10/31/2019',
    },
    {
      kind: 'BATCH CONTROL RECORD',
      count: '8',
      entryCount: '18',
      entryHash: '0181800018',
      debitEntryAmount: '$0.00',
      creditEntryAmount: '$0.83',
      companyId: '741258964',
    },
    {
      kind: 'FILE CONTROL RECORD',
      count: '9',
      entryCount: '18',
      entryHash: '0181800018',
      debitEntryAmount: '$0.00',
      creditEntryAmount: '$0.83',
    },
  ];

  return (
    <Section header="Company Specification / File Details">
      {records.map((record, index) => (
        <RecordView
          key={index}
          record={record}
          errors={errors}
        />
      ))}
    </Section>
  );
};

export default function DetailView() {
  const errors: RecordError[] = [
    {
      field: 'debitEntryAmount',
      reason: 'BATCH CONTROL RECORD (8) TTL Debit Entry $-Amount Does NOT Match Entry Totals',
    },
    {
      field: 'creditEntryAmount',
      reason: 'BATCH CONTROL RECORD (8) TTL Credit Entry $-Amount Does NOT Match Entry Totals',
    },
    {
      field: 'debitEntryAmount',
      reason: 'FILE CONTROL RECORD (9) TTL Debit Entry $-Amount Does NOT Match Entry Totals',
    },
    {
      field: 'creditEntryAmount',
      reason: 'FILE CONTROL RECORD (9) TTL Credit Entry $-Amount Does NOT Match Entry Totals',
    },
  ];

  return (
    <>
      <MessageContentView errors={errors} />
      <DetailContentView errors={errors} />
    </>
  );
}