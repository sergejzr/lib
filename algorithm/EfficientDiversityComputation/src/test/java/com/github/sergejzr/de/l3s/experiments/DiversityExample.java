package com.github.sergejzr.de.l3s.experiments;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import com.github.sergejzr.de.l3s.rdj.document.Diversity;
import com.github.sergejzr.de.l3s.rdj.document.Document;
import com.github.sergejzr.de.l3s.rdj.impl.AllPairsDJ;
import com.github.sergejzr.de.l3s.rdj.impl.SampleDJ;
import com.github.sergejzr.de.l3s.rdj.impl.TrackDJ;
import com.github.sergejzr.de.l3s.rdj.impl.TrackDJReadable;
import com.github.sergejzr.de.l3s.toolbox.JaccardSimilarityComparator;

public class DiversityExample
{

    public DiversityExample()
    {
    }

    public static void main(String args[])
    {
        Vector collection = generateCollection(1000, 10, 20);
        double error = 0.05;
        double confidentiality = 0.95;
        JaccardSimilarityComparator similarityComparator = new JaccardSimilarityComparator();
        Date now=new Date();
        double dj;
        Diversity dj1 = new AllPairsDJ(collection, error, confidentiality, similarityComparator);
        dj=dj1.getRDJ();
        System.out.println((new StringBuilder("RDJ:("+(new Date().getTime()-now.getTime())+"ms)")).append(dj).toString());
        now=new Date();
        Diversity dj2 = new SampleDJ(collection, error, confidentiality, similarityComparator);
        dj=dj2.getRDJ();
        System.out.println((new StringBuilder("SampleRDJ:("+(new Date().getTime()-now.getTime())+"ms)")).append(dj).toString());
        now=new Date();
        Diversity dj3 = new TrackDJ(collection, error, confidentiality);
        dj=dj3.getRDJ();
        System.out.println((new StringBuilder("TrackRDJ:("+(new Date().getTime()-now.getTime())+"ms)")).append(dj).toString());
        Diversity dj4 = new TrackDJReadable(collection, error, confidentiality);
        dj=dj4.getRDJ();
        System.out.println((new StringBuilder("TrackRDJ:("+(new Date().getTime()-now.getTime())+"ms)")).append(dj).toString());
  
        
    
    
    }

    private static Vector generateCollection(int numOfDocuments, int maxDoclength, int vocabularySize)
    {
        Vector collecion = new Vector();
        Random r = new Random();
        int line[][] = new int[numOfDocuments][];
        for(int i = 0; i < line.length; i++)
        {
            int len = r.nextInt(maxDoclength) + 1;
            Document d = new Document();
            line[i] = new int[len];
            for(int y = 0; y < len; y++)
            {
                int curval = r.nextInt(vocabularySize);
                d.add((new StringBuilder(String.valueOf(curval))).toString());
                line[i][y] = curval;
            }

            Arrays.sort(line[i]);
            collecion.add(d);
        }

        return collecion;
    }
}
