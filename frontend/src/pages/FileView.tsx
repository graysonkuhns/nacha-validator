import React, { useState, useRef } from 'react';
import { Grid } from '@material-ui/core';

interface GrayButtonProps {
  onClick: () => void;
  text: string;
  disabled?: boolean;
}

const GrayButton: React.FC<GrayButtonProps> = ({ onClick, text, disabled }) => {
  return (
    <button onClick={onClick} style={{
      backgroundColor: '#ababab',
      border: '2px solid #292828',
      boxShadow: '0px 4px 4px 1px #ccc',
      color: disabled ? 'grey' : 'black',
      fontSize: '1.7em',
    }}>
      <div style={{
        paddingTop: '0.1em',
        paddingBottom: '0.1em',
        paddingRight: '2.5em',
        paddingLeft: '2.5em',
      }}>
        {text}
      </div>
    </button>
  )
};

const FilePreview: React.FC<{content: string | null}> = ({content}) => {
  if (!content)
    return null;

  return (
    <textarea
      value={content}
      rows={30}
      cols={95}
      wrap="hard"
      readOnly
      style={{
        width: '100%',
        minWidth: '100%',
        textAlign: 'center',
        fontFamily: '"Courier New", Courier, monospace',
        fontSize: '1.1em',
      }}
    />
  );
};

interface FileViewProps {
  onLoad: (content: string) => void;
  onClear: () => void;
}

export default function FileView({ onLoad, onClear }: FileViewProps) {
  const fileUploadRef = useRef<HTMLInputElement>();
  const [content, setContent] = useState<string | null>(null);

  const handleFileUpload = (event: any) => {
    const reader = new FileReader();
    reader.onload = (readEvent: any) => {
      const fileContent = readEvent.target.result;
      setContent(fileContent);
      onLoad(fileContent);
      if (fileUploadRef.current) {
        const file = fileUploadRef.current as any;
        file.value = '';
      }
    };
    reader.readAsText(event.target.files[0]);
  };

  const handleCancelClicked = () => {
    setContent(null);
    onClear();
  };

  return (
    <div style={{ border: '1px solid grey', minWidth: '90%', }}>
      <Grid container direction="column">
        <Grid item xs={12}>
          <FilePreview content={content} />
        </Grid>
        <Grid item xs={12}>
          <Grid container direction="row" justify="space-around" style={{ padding: '1em' }}>
            <input
              hidden
              type="file"
              accept="text/*"
              onChange={handleFileUpload}
              ref={input => fileUploadRef.current = input || undefined}
            />
            <GrayButton
              text="Browse"
              disabled={content !== null}
              onClick={() => fileUploadRef.current?.click()}
            />
            <GrayButton
              text="Cancel"
              disabled={content === null}
              onClick={handleCancelClicked}
            />
            <GrayButton
              text="Validate File"
              disabled
              onClick={() => {}}
            />
          </Grid>
        </Grid>
      </Grid>
    </div>
  );
}