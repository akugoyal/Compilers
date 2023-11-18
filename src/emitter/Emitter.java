package emitter;

import java.io.*;

/**
 * Emitter class for writing code to a file
 *
 * @author Akul Goyal
 * @version 11-18-2023
 */
public class Emitter
{
    private BufferedWriter out;
    private int labelID;

    /**
     * Creates an Emitter for writing to a new file with given name
     *
     * @param outputFileName the name of the file to write to
     */
    public Emitter(String outputFileName)
    {
        labelID = 0;
        try
        {
            out = new BufferedWriter(new FileWriter(outputFileName));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes one line of code to file (with non-labels indented)
     *
     * @param code    the code to emit
     * @param comment the comment to include above the code
     */
    public void emit(String code, String comment)
    {
        try
        {
            if (!code.endsWith(":"))
            {
                code = "\t" + code;
            }
            if (comment.length() > 0)
            {
                comment = "\t\t# " + comment;
            }
            out.write(code + comment + "\n");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException
    {
        out.flush();
        out.close();
    }

    /**
     * Emits code to push a register onto the stack
     *
     * @param reg the register to push
     */
    public void emitPush(String reg)
    {
        emit("subu $sp $sp 4", "");
        emit("sw " + reg + " ($sp)", "push " + reg);
    }

    /**
     * Emits code to pop to a register from the stack
     *
     * @param reg the register to pop to
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg + " ($sp)", "");
        emit("addu $sp $sp 4", "pop to " + reg);
    }

    /**
     * Emits code to create a data label
     *
     * @param id    the name of the label
     * @param type  the type of the label
     * @param value the value of the label
     */
    public void emitData(String id, String type, String value)
    {
        emit(id + ":\t." + type + "\t" + value, "");
    }

    /**
     * Returns the ID of the next label to be created
     *
     * @return the ID of the next label to be created
     */
    public int nextLabelID()
    {
        labelID++;
        return labelID;
    }
}