Cipher text : the encryption text  
Key : value that sets the output of an encryption function  
Plain Text : the original text  
Salt : value to enhance the randomness of a one-way function  
Digital Signature : mathematical function to verify authenticity  
Encryption : two-way mathematical function to mask data  
Hash : one-way mathematical function to map data  
JCA : java cryptography Architecture  
JCE : java cryptography extensions  


understanding encryption
* process by which plain data becomes hidden
* ciphered data becomes difficult, hopefully impossible, to read without the key
* therefore, only authorized parties can decrypt


Block cipher Modes
* cipher block chaining(CBC) combines previous block cipher with current block plain text
* Uses initialization vector
* Cipher feedback (CFB), output feedback(OFB) and electronic code book(ECB) less common

Key size
* key size within an algorithm increase security
* key size varies by algorithm, especially in Asymmetric examples
* sufficient key size is critical


symmetric encryption 
* shared key

Asymmetric encryption
* public/private key pair


Performance considerations
- shared key block and stream ciphers are relatively quick
- PKI is extremely slow
- Usually PKI is used solely to transfer a shared key: TLS  

#### Secure random
```
- engine to create cryptographically strong numbers
- different than java.lang.Random
- Requirement for good crypto operations
- seeds for keys and other algorithm needs
```

#### Message Digest
- Engine to produce cryptographically secure hashes
- One-way operations
- Fix-sized output from variable-sized input

#### Signature
```
- Engine to create and validate digital signatures
- Combination of hash
- Very useful in identity cases
```

#### Cipher
- Engine to provide encryption
- Asymmetric and symmetric encryption support
- Stream and block ciphers
- padding on block ciphers

#### Stores
```
- keystore: store keys
- Truststore: used to store certificate
- keytool: most often used for working with these
```


#### Symmetric Uses 
- Data at rest
- Data in motion after initial handshake
- stream and block in use; however, most stream are out of scope(i.e, RC4 is cracked)

#### Asymmetric Uses
```
- TLS
- Digital signatures
- certificate trusts
```

#### Cryptography Hash
- hash function that takes arbitrary input and produces fixed-sized output
- Easy to calculate
- Difficult to find original input
- Difficult to duplicate with unique inputs
- Digital Fingerprint
- Digest
- Message Digest
- Checksum


#### Cracking Hash
```
- Finding an algorithm to generate a collision between two hashes
- Finding an algorithm to identify a unique and different input that will yield a given hash
- MD5, SHA-1 considered "cracked"
```

#### Hash Uses
- Digital Signatures
- Digital Fingerprint
- Logging Sensitive Data
- Saving Password



### Securing Password
Baseline :
- never store encrypted passwords
- never use broken hash algorithm
- never store in plain text

Good password hashing algorithms
- not cracked
- not susceptible to brute force
- inherent slowness

Broken:
- MD5: cracked and fast
- SHA1: cracked and fast
- SHA2: fast
- SHA3: not a standard, fast

Possibilities :
- PBKDF2
- BCrypt
- SCrypt

### Digital Signature
- asymmetrically encrypted hash of a digital payload
- a value that can provide guarantee of authenticity
- a value that can provide a mechanism of non repudiation
- a value that can provide a guarantee of integrity

 How is a digital signature calculated ?
 - Asymmetric key pair created
 - Hash created
 - Hash encrypted with private key
 - verification includes rehashing and matching to decryption
 
 Digital signature flow
 - ```A``` calculates hash of message
 - ```A``` encrypts using private key and sends signature and message to ```B```
 - ```B``` hashes message
 - ```B``` decrypt signature with ```A``` public key and compares
 
 ### Java Keystore
 
#### Building Java keystore
- usually build using java keytool
- can be built in code
- often useful when scripting DevOps activities and internally signed certificate

JCA keystore object
- Class for interacting with java KeyStore files
- Can be purely in memory or literally on disk
- Can leverage .pks files or .p12 (PKCS#12)

TrustStore vs KeyStore
- TrustStore is a keyStore
- Usually only used for certificates from a CA
- Can contain public CA signed certs
- Can contain private CA signed certs


Bouncy Castle
- Open source project
- Australian charity organization
- Legion of the Bouncy castle
- Java and C# implementations
- JCE provider
- Full crypto library
- Full api for working with TLS and certs

