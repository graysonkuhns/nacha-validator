import React from 'react';

const Section: React.FC<{ header: string }> = ({ header, children }) => {
  return (
    <>
      <div style={{
        color: 'white',
        padding: '5px',
        minWidth: '100%',
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

const MessageContentView: React.FC = () => {
  const messages = [
    'BATCH CONTROL RECORD (8) TTL Debit Entry $-Amount Does NOT Match Entry Totals',
    'BATCH CONTROL RECORD (8) TTL Credit Entry $-Amount Does NOT Match Entry Totals',
    'FILE CONTROL RECORD (9) TTL Debit Entry $-Amount Does NOT Match Entry Totals',
    'FILE CONTROL RECORD (9) TTL Credit Entry $-Amount Does NOT Match Entry Totals',
  ];

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
          {messages.map((msg, index) => (
            <span key={index}>
              {msg}
            </span>
          ))}
        </div>
      </div>
    </Section>
  );
};

const DetailContentView: React.FC = () => {
  return (
    <Section header="Company Specification / File Details">
      Placeholder
    </Section>
  );
};

export default function DetailView() {
  return (
    <>
      <MessageContentView />
      <DetailContentView />
    </>
  );
}